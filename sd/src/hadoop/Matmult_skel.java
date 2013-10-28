import java.io.IOException;
import java.util.*;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

public class Matmult {

    // Map class
    // 
    public static class Map1 extends MapReduceBase implements 
        Mapper<LongWritable, Text, Text, IntWritable> { 

        // The key
        private Text key = new Text();


	  public void map(LongWritable key, Text value, 
				OutputCollector<Text, IntWritable> output, Reporter reporter) 
		    throws IOException {

				...  
					  key.set( myKey );
				...
					  // associate these values to the key 
					  output.collect(key, values ); // values : use for example  'new IntWritable(str)'
		    }
	  } 

	// A Reduce class
	public static class Reduce1 extends MapReduceBase 
implements Reducer<Text, IntWritable, Text, IntWritable> {

        // prepare a new Key
        private Text key = new Text();

        public void reduce(Text key, Iterator<IntWritable> values, 
            OutputCollector<Text, IntWritable> output, Reporter reporter) 
            throws IOException {

	      // do something with the values
            while(values.hasNext()) {
                    some function of  values.next().get();
            }

		// If the key is to be modified, it can be get and set by:
            String str = key.toString();
            key.set( modification of str );

            // Re-emit the key and associated values
            output.collect(key, values );
        }
    }


    // Main
    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(prodMat.class);
        conf.setJobName("prodmat");
    
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
    
        conf.setMapperClass(Map1.class);
        conf.setCombinerClass(Reduce1.class);
    
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}
