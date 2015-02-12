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


	
	public static class Map extends
			Mapper<LongWritable, Text, Text, Text> {


			/**
			  * Each map is provided with one line read, sotred value has the form
		   	  * <node>\t<dist>\t<succList>
			  * where:
			  * <node> is a string
			  * <dist> is an integer representing the shortest 
			  * <succList> - either a list of pairs <snode>,<sdist> separated by ;
			  * 		     where snode is a successor node, 
			  *              and sdist the distance from node to snode.
		        *            - or ? when successors are unknown yet
			  *		   - or {} when there is no successor
			  * e.g 
			  *  		A	0	B,3;C,4;D,1 
			  **/


		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {			

			String outerNodeDelim=";";  // e.g  A,2;B,3
			String innerNodeDelim=",";   // e.g  A,2 
			String emptySet="{}";  
			String unknown="?";  
			
			System.out.println("Map --> received "+ value.toString());
			String[] values = value.toString().split("\t");
			String node     = values[0];			
			Long   dist     = Long.valueOf(values[1]);
			String succList = values[2];		
			String[] snodes = null;
			
		      // default output string when re-emitting what we reveived	
			String outputValue = dist + "\t" + succList; 	

			// If the ditance is unknown (synoym of and unvisited node), re-emit the 
			// relation as is not to loose information about the graph's structure. 
			if ( dist >= 999 ) {
				// split the input value in key,value
				System.out.println("emit(\""+node+"\",\""+outputValue+"\")");
				context.write(new Text(node), new Text(outputValue));
			}
			else {
				// We receive a line with a known distance from the origin:
				// Two cases :
				// - successors are known : for each successor, emit the ditance once we have moved to it.
				// - successors are unknown : re-emit to preserve this distance
				if (succList.equals(unknown) || succList.equals(emptySet)) {
					System.out.println("emit(\""+node+"\",\""+outputValue+"\")");
					context.write(new Text(node), new Text(outputValue));
				}
				else {
					snodes = succList.split(outerNodeDelim);
					System.out.println("--> #snodes="+ snodes.length);
					for (String n : snodes) {
					  	// A pair (NodeName,dist) list of succesors is expected
					    	String[] nodeNameDist = n.split(innerNodeDelim);
					      Long sdist = Long.valueOf(nodeNameDist[1]);
					    	Long newDist = sdist + dist; 
						outputValue = newDist.toString() + "\t" + unknown;
					    	System.out.println("emit(\""+nodeNameDist[0]+"\",\""+outputValue+"\")");
					    	context.write(new Text(nodeNameDist[0]), new Text(outputValue));
					}
				}
			}
		}
	}

	public static class Reduce extends
			Reducer<Text, Text, Text, Text> {

		@Override
		public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {		

			String emptySet="{}";  
			ArrayList<String> paths = new ArrayList<String>();
			Long minDist = Long.MAX_VALUE;
			String valParts []; // to parse each values item received
			//Get the min distance received for that key
			
			System.out.println("-> Reduce : key="+key.toString());
			for (Text val : values) {
			      // We scan a list of elements of type '<distance>	 <list of successor>'	
				//System.out.println("--> key:"+key.toString()+" Reduce : examines "+val.toString());
				valParts = val.toString().split("\t");
				paths.add( valParts[1] );
				if (Long.valueOf(valParts[0]) < minDist){
					minDist = Long.valueOf(valParts[0]);
				}
			}
			// Now re-emit all paths found that allow to hop from that key to successors
			for (String p : paths) {
				//if (!p.equals(emptySet)) { // restrict to significant path except terminal node
					System.out.println("--> Reduce : key="+key.toString()+" emit(\""+key.toString()+"\",\""+minDist +"\t"+p +"\")");
					context.write(key,new Text(minDist +"\t"+p));
				//}
			}
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
				// just bypass (overwrite) existing file
			}
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		
		//Creates a MapReduce Job
		Configuration conf = new Configuration();		
		
	      // We should iterate a maximum of D times, where D is the graph's diameter.
		// Indeed, each map phase makes one hop to all successors of a given node.
		// * Problem * : we are not likely to be given the graph's diameter.

		for (int i=0;i<3;i++) {	
			  Job job = new Job(conf, "SP");

			  job.setOutputKeyClass(Text.class);
			  job.setOutputValueClass(Text.class);

			  job.setMapperClass(Map.class);
			  job.setReducerClass(Reduce.class);

			  job.setInputFormatClass(TextInputFormat.class);

			  //Uses the custom output format
			  job.setOutputFormatClass(MyTextOutputFormat.class);

			  //The input files has to contain the first distance and node separated by a tabulation (for exemple: A	0)
			  if (i>=1) {
				    FileInputFormat.addInputPaths(job, args[1]+"/"+"part-r-00000");
			  }
			  else
				    FileInputFormat.addInputPath(job, new Path(args[0]));
			  FileOutputFormat.setOutputPath(job, new Path(args[1]));

			  //Run the job at least one to have the output file
			  job.waitForCompletion(true);

		}
/*		
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
*/
	}
	

		
}