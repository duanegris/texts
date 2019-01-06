/**
  * Demonstration of Hadoop way of reading input data
  *
  * S. Genaud
  **/

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class SplitRead {

  public static class ReadMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    
	    public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

			InputSplit split = context.getInputSplit();
			/* FileSplit derives from InputSplit : It is the file we are working on */
			FileSplit fsplit = (FileSplit) split;
			System.out.println("* Map spawned: working on data split from ");
			System.out.println("  - file   : "+fsplit.getPath());
			System.out.println("  - offset : "+fsplit.getStart());
			System.out.println("  - length : "+fsplit.getLength());
			System.out.println("  - (key,value)=("+key+","+value+")");
	    }
  }
  

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
    FileSystem fs = FileSystem.newInstance(conf);

    if (otherArgs.length != 2) {
      System.err.println("Usage: prog <in> <out>");
      System.exit(2);
    }
    Job job = new Job(conf, "word count");
    job.setJarByClass(SplitRead.class);
    job.setMapperClass(ReadMapper.class);
    job.setOutputKeyClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(otherArgs[0]));
    FileOutputFormat.setOutputPath(job, new Path(otherArgs[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
