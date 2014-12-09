import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.FileAlreadyExistsException;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.JobContext;
import org.apache.hadoop.mapreduce.Mapper;
//import org.apache.hadoop.mapred.lib.IdentityMapper; // Well, this is from the 0.x

import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class NodeDegree {

	public static class MapFirstStep extends
			Mapper<LongWritable, Text, Text, Text> {

		/** 
		 * First map.
		 * The edges denoted as a pair of vertices (=an edge)  are split into nodes. Each node 
             * is sent to the reducer together with the edge it belongs to. 
		 * @param key is a line number in the input file (of no use for us)
 		 * @param value is the line content : expected format is: (U,V) for vertices U and V
		 * @return two (k,v) pairs are output: ("U","(U,V)") and ("V","(U,V)")
		 **/
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {				
			String[] nodes = value.toString().replace("(", "").replace(")","").split(",");
			context.write(new Text(nodes[0]), value);
			context.write(new Text(nodes[1]), value);
		}
	}


	public static class ReduceFirstStep extends
			Reducer<Text, Text, Text, Text> {

		/**
		 * First reduce.
		 * Counts the number of times a node is encountered. It determines its degree.
		 * The degree is send back with its associated edge
		 * @param key a vertex
		 * @param values an iterator on the vertices pairs (=edges) associated to key
		 * @return 
		 **/
		public void reduce(Text key, Iterable<Text> values, Context context) 
			throws IOException, InterruptedException {		
			Integer degree = 0;
			List<Text> valuesList = new ArrayList<Text>();
			for(Text value : values){
				// The Iterable over Text seems to consume values, so save them
				valuesList.add(new Text(value.toString()));
				degree++;
			}
			// Emit the sum of occurences (degree) found for each edge
			for(Text value: valuesList){
				context.write(new Text(value), new Text("d("+key+")="+degree));
			}			
		}
	}
	
	public static class IdentityMap extends Mapper<LongWritable, Text, Text, Text> {

		// Though identity is the default, see
 		// https://hadoop.apache.org/docs/current/api/org/apache/hadoop/mapreduce/Mapper.html#map(KEYIN,%20VALUEIN,%20org.apache.hadoop.mapreduce.Mapper.Context)
		// we define our identity to cast LongWritable key to Text

		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {			
			String[] values = value.toString().split("\t");
			context.write(new Text(values[0]), new Text(values[1]));
		}

	}


	public static class ReduceSecondStep extends
	Reducer<Text, Text, Text, Text> {

		/**
		 * Second reduce.
		 * Merge all the edges and display the two individual vertex's degree 
		 * (i.e  d(U)=x) all in one line
		 * @param key a edge (a vertices pair)
		 * @param values the individual degrees : [d(U)=x, d(V)=y] 
		 **/
		public void reduce(Text key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {	
			String degreesLine="\t";
			for(Text value : values){
				degreesLine += value + "\t"; 
			}
			context.write(key, new Text(degreesLine));
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
		
		//Creates a MapReduce Job
		Configuration conf = new Configuration();		
				
		Job job1 = new Job(conf, "My NodeDegree phase1");

		job1.setOutputKeyClass(Text.class);
		job1.setOutputValueClass(Text.class);

		job1.setMapperClass(MapFirstStep.class);
		job1.setReducerClass(ReduceFirstStep.class);

		job1.setInputFormatClass(TextInputFormat.class);
		
		// Uses our custom output format to override already existing file.
		// Extends TextOutputFormat which emits LongWritable key and Text value by default.
		job1.setOutputFormatClass(MyTextOutputFormat.class);
				

		FileInputFormat.addInputPath(job1, new Path(args[0]));
		FileOutputFormat.setOutputPath(job1, new Path(args[1]));
		
		//Tell the jobtracker to run the first job and wait for completion
		job1.waitForCompletion(true);
		
		Job job2 = new Job(conf, "My NodeDegree phase 2");

		job2.setOutputKeyClass(Text.class);
		job2.setOutputValueClass(Text.class);

        	job2.setMapperClass(IdentityMap.class);
		job2.setReducerClass(ReduceSecondStep.class);

		job2.setInputFormatClass(TextInputFormat.class);
		
		//Uses the custom output format to override directory protection
		job2.setOutputFormatClass(MyTextOutputFormat.class);

		//Takes information from the previous job
		FileInputFormat.addInputPaths(job2, args[1]+"/"+"part-r-00000");
		FileOutputFormat.setOutputPath(job2, new Path(args[1]));
		
		//Run the second job
		job2.waitForCompletion(true);
		
	}

}
