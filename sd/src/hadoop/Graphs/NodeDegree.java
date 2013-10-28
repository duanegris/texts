package org.myorg;

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
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;

public class NodeDegree {

	public static class MapFirstStep extends
			Mapper<LongWritable, Text, Text, Text> {

		//First mapping
		//The edges are split into nodes
		//Each node is send to reduce (with the edge that it comes from as value)
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {				
			String[] nodes = value.toString().replace("(", "").replace(")","").split(",");
			context.write(new Text(nodes[0]), value);
			context.write(new Text(nodes[1]), value);
		}
	}

	public static class ReduceFirstStep extends
			Reducer<Text, Text, Text, Text> {

		//First reduce
		//Counts the number of times a node is encountered. It determines its degree.
		//The degree is send back with its associated edge
		public void reduce(Text key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {		
			Integer degree = 0;
			List<Text> valuesList = new ArrayList<Text>();
			for(Text value : values){
				valuesList.add(new Text(value.toString()));
				degree++;
			}
			for(Text value: valuesList){
				context.write(value, new Text("d("+key+")="+degree));
			}			
		}
	}
	
	public static class MapSecondStep extends
	Mapper<LongWritable, Text, Text, Text> {

		//Second map: does nothing but is mandatory by hadoop
		public void map(LongWritable key, Text value, Context context)
				throws IOException, InterruptedException {			
			String[] values = value.toString().split("\t");
			context.write(new Text(values[0]), new Text(values[1]));
		}
	}

	public static class ReduceSecondStep extends
	Reducer<Text, Text, Text, Text> {

		//Merge all the data so it is display without duplicates
		public void reduce(Text key, Iterable<Text> values,
				Context context) throws IOException, InterruptedException {	
			//The value of each node
			String value1 = null;
			String value2 = null;
			String[] nodes = key.toString().replace("(", "").replace(")","").split(",");
			for(Text value : values){
				//Find the value for each node and eliminates duplicates
				if(value1 == null){
					value1 = value.toString();
				}
				else{
					if(!value.equals(value1)){
						value2 = value.toString();
					}
				}
			}
			if(value2 == null){
				value2 = "";
			}
			//Sets the value in the order of the associated edge 
			if(!nodes[0].equals(value1.substring(2, value1.indexOf(")")))){
				String tmp;
				tmp = value2;
				value2 = value1;
				value1 = tmp;
			}
			context.write(key, new Text(value1 + "\t" + value2));
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
				
		Job jobFirstPart = new Job(conf, "minpath");

		jobFirstPart.setOutputKeyClass(Text.class);
		jobFirstPart.setOutputValueClass(Text.class);

		jobFirstPart.setMapperClass(MapFirstStep.class);
		jobFirstPart.setReducerClass(ReduceFirstStep.class);

		jobFirstPart.setInputFormatClass(TextInputFormat.class);
		
		//Uses the custom output format
		jobFirstPart.setOutputFormatClass(MyTextOutputFormat.class);

		FileInputFormat.addInputPath(jobFirstPart, new Path(args[0]));
		FileOutputFormat.setOutputPath(jobFirstPart, new Path(args[1]));
		
		//Run the first job
		jobFirstPart.waitForCompletion(true);
		
		
		Job jobSecondPart = new Job(conf, "minpath");

		jobSecondPart.setOutputKeyClass(Text.class);
		jobSecondPart.setOutputValueClass(Text.class);

		jobSecondPart.setMapperClass(MapSecondStep.class);
		jobSecondPart.setReducerClass(ReduceSecondStep.class);

		jobSecondPart.setInputFormatClass(TextInputFormat.class);
		
		//Uses the custom output format
		jobSecondPart.setOutputFormatClass(MyTextOutputFormat.class);

		//Takes information from the previous job
		FileInputFormat.addInputPaths(jobSecondPart, args[1]+"/"+"part-r-00000");
		FileOutputFormat.setOutputPath(jobSecondPart, new Path(args[1]));
		
		//Run the second job
		jobSecondPart.waitForCompletion(true);
		
		
		
	}

}