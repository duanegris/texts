package org.myorg;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileAlreadyExistsException;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class ShortestPath {

	/**
	 * Graph containing all distances between two nodes
	 */
	private static HashMap<String, Integer> graphDistances = new HashMap<String, Integer>();
	
	/**
	 * The graph indicating the connection between the nodes
	 */
	private static HashMap<String, List<String>> graphNodes = new HashMap<String, List<String>>();
	
	/**
	 * Graph used to have a end condition
	 */
	private static HashMap<String, Boolean> checkedNodes = new HashMap<String, Boolean>();
	
	public static class Map extends
			Mapper<LongWritable, Text, Text, LongWritable> {
		private Text outGoingNode = new Text();

		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {			
			//The key is composed of the node and the distance used to get to it
			String[] values = value.toString().split("\t");
			String nodeName = values[0];			
			Long distance = Long.valueOf(values[1]);
			
			//Takes all the node that are pointed by the key node
			List<String> nodeList = graphNodes.get(nodeName);
			
			//If the node is not checked (its shortest path has been modify or it has never been reached)
			//then it will send a message to all of its pointed nodes
			if(checkedNodes.get(nodeName) == null){
				checkedNodes.put(key.toString(), true);
				if(nodeList != null){
					//For each node the total distance to this node is calculated and send for reduce
					for(String node : nodeList){
						outGoingNode.set(node);
						Integer distanceToNextNode;
						if(node.equals(nodeName)){
							distanceToNextNode = 0;
						}
						else{
							distanceToNextNode = graphDistances.get(nodeName+","+node);
						}
						LongWritable nodeValue = new LongWritable(distance + distanceToNextNode);
						context.write(outGoingNode, nodeValue);
					}	
				}
			}
			//The node sends a message pointing to itself (in order to have the distances to all node in the final result)
			outGoingNode.set(nodeName);
			context.write(outGoingNode, new LongWritable(distance));
		}
	}

	public static class Reduce extends
			Reducer<Text, LongWritable, LongWritable, Text> {

		public void reduce(Text key, Iterable<LongWritable> values,
				Context context) throws IOException, InterruptedException {		
			int numberOfValues = 0;			
			long min = Long.MAX_VALUE;
			//Gets the min value from the data
			for (LongWritable val : values) {
				if(val.get() < min){
					min = val.get();
				}
				numberOfValues++;
			}
			//If there is more than one value send for reduction, then the node has been modified or has been reached for the first time
			if(numberOfValues > 1){
				checkedNodes.remove(key.toString());
			}
			//Sends the result which is the node and the currents shortest path to it
			context.write(null, new Text(key.toString() + "\t" + min));
		}
	}
	
	public static class MyTextOutputFormat<A, B> extends TextOutputFormat<A, B>{

		//This allows to bypass the already existing output folder exception
		@Override
		public void checkOutputSpecs(JobContext job)
				throws FileAlreadyExistsException, IOException {
			try{
				super.checkOutputSpecs(job);
			}
			catch (FileAlreadyExistsException e){				
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		//Creates the graph (all the nodes and distances)
		initGraph();
		//initGraph2();
		
		//Creates a MapReduce Job
		Configuration conf = new Configuration();		
				
		Job job = new Job(conf, "minpath");

		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(LongWritable.class);

		job.setMapperClass(Map.class);
		job.setReducerClass(Reduce.class);

		job.setInputFormatClass(TextInputFormat.class);
		
		//Uses the custom output format
		job.setOutputFormatClass(MyTextOutputFormat.class);

		//The input files has to contain the first distance and node separated by a tabulation (for exemple: A	0)
		FileInputFormat.addInputPath(job, new Path(args[0]));
		FileOutputFormat.setOutputPath(job, new Path(args[1]));
		
		//Run the job at least one to have the output file
		job.waitForCompletion(true);
		
		while(checkedNodes.size() < graphNodes.size()){		
			job = new Job(conf, "minpath");

			job.setOutputKeyClass(Text.class);
			job.setOutputValueClass(LongWritable.class);

			job.setMapperClass(Map.class);
			job.setReducerClass(Reduce.class);

			job.setInputFormatClass(TextInputFormat.class);
			job.setOutputFormatClass(MyTextOutputFormat.class);

			//Reuses the output file to launch another Map/Reduce
			FileInputFormat.addInputPaths(job, args[1]+"/"+"part-r-00000");
			FileOutputFormat.setOutputPath(job, new Path(args[1]));
			job.waitForCompletion(true);
		}
	}
	
	private static void initGraph(){
		
		List<String> nodeA = new ArrayList<String>();
		nodeA.add("B");
		nodeA.add("C");
		nodeA.add("D");
		graphNodes.put("A", nodeA);
		
		List<String> nodeB = new ArrayList<String>();
		nodeB.add("E");
		graphNodes.put("B", nodeB);
		
		List<String> nodeC = new ArrayList<String>();
		nodeC.add("G");
		graphNodes.put("C", nodeC);
		
		List<String> nodeD = new ArrayList<String>();
		nodeD.add("F");
		graphNodes.put("D", nodeD);
		
		List<String> nodeE = new ArrayList<String>();
		nodeE.add("G");
		graphNodes.put("E", nodeE);
		
		List<String> nodeF = new ArrayList<String>();
		nodeF.add("C");
		graphNodes.put("F", nodeF);
		
		List<String> nodeG = new ArrayList<String>();
		graphNodes.put("G", nodeG);
		
		graphDistances.put("A,B", 3);
		graphDistances.put("A,C", 4);
		graphDistances.put("A,D", 1);
		graphDistances.put("B,E", 2);
		graphDistances.put("C,G", 5);
		graphDistances.put("D,F", 1);
		graphDistances.put("E,G", 3);
		graphDistances.put("F,C", 3);
	}
	
	private static void initGraph2(){
		List<String> nodeA = new ArrayList<String>();
		nodeA.add("G");
		nodeA.add("B");
		graphNodes.put("A", nodeA);
		
		List<String> nodeB = new ArrayList<String>();
		nodeB.add("C");
		graphNodes.put("B", nodeB);
		
		List<String> nodeC = new ArrayList<String>();
		nodeC.add("D");
		graphNodes.put("C", nodeC);
		
		List<String> nodeD = new ArrayList<String>();
		nodeD.add("E");;
		graphNodes.put("D", nodeD);
		
		List<String> nodeE = new ArrayList<String>();
		nodeE.add("F");
		graphNodes.put("E", nodeE);
		
		List<String> nodeF = new ArrayList<String>();
		nodeF.add("G");
		graphNodes.put("F", nodeF);
		
		List<String> nodeG = new ArrayList<String>();
		graphNodes.put("G", nodeG);
		
		graphDistances.put("A,B", 1);
		graphDistances.put("A,G", 100);
		graphDistances.put("B,C", 1);
		graphDistances.put("C,D", 2);
		graphDistances.put("D,E", 5);
		graphDistances.put("E,F", 1);
		graphDistances.put("F,G", 3);	
	}

}
