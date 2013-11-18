/**
 * Matrix Multiplication.
 * Consider matrices A (L,M) and B (M,N), we want to compute resulting matrix 
 * A x B = C (L,N)
 *
 **/


import java.io.IOException;
import java.util.*;
 
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
 
public class Mm {


    public static class Map extends Mapper<LongWritable, Text, Text, Text> {

    /**
     * map : the map function implementation
     *
     * @param key
     * @param value  the data from input files, one line per map invocation, as text
     * @param contex a place to write the (key,value) pairs we want to emit 
     **/
        public void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

	    // Matrix description is expected to be formatted as:
	    // A : [a_1,1 a_1,2 ... a_1,L] [a_2,1 ... a_2,L] ... [a_M,1, ... a_M,L]
	    // B : [b_1,1  ... b_1,M] [b_2,1 ... b_2,M] ... [b_N,1, ... B_N,M]
	    // that is : each matrix value is separated by a blank, 
	    // lines are between [ and ]  
	    // Matrices names are preceding values, with a ':' as separator between name and values
	    // The 2 matrices on a separated line each

            Text outputKey = new Text();
            Text outputValue = new Text();
            Configuration conf = context.getConfiguration();
            int L = Integer.parseInt(conf.get("L"));
            int M = Integer.parseInt(conf.get("M"));
            int N = Integer.parseInt(conf.get("N"));
		String [][] A = new String [L][M];
		String [][] B = new String [M][N];
		// use a regexp to separate each line (starts with [ or [[ )
		// We should have L*M + M*N strings after this split
            String [] matStruct = value.toString().split(":");  // name 
										 // [ .. ] .... [....]
		matStruct[0]= matStruct[0].trim();
		matStruct[1]= matStruct[1].trim();
		
            String [] matLines = matStruct[1].split ("\\]");   // [a_1,1 a_1,2 ... a_1,L 
                                                            //  [a_2,1 ... a_2,L 
										// or
										//  [b_1,1  ... b_1,M
										//  [b_N,1, ... B_N,M

		// A
		System.out.println("-----\n@"+matStruct[0]+"@\n------");
		if (matStruct[0].trim().equals("A")) {
			for (int i=0;i<L;i++) {
  				// remove leading ']' (replace doesn't use a regexp so no protection)
				matLines[i] = matLines[i].replace("[","").trim();
				System.out.println(matLines[i]);
				A[i] = matLines[i].split(" ");
				for (int j=0;j<M;j++) {
					outputKey.set("C_"+i+"_"+j);
					outputValue.set(A[i][j]);
                    		context.write(outputKey, outputValue);
				}
				
			}
		}
		// B
		if (matStruct[0].trim().equals("B")) {
			for (int i=0;i<M;i++) {
  				// remove leading ']' (replace doesn't use a regexp so no protection)
				matLines[i] = matLines[i].replace("[","").trim();
				System.out.println(matLines[i]);
				B[i] = matLines[i].split(" ");
			}
		}
		    
             //   for (int k = 0; k < M; k++) {
             //       outputKey.set(indicesAndValue[1] + "," + k);
             //       outputValue.set("A," + indicesAndValue[2] + "," + indicesAndValue[3]);
            //        context.write(outputKey, outputValue);
           //     }
           // } else {
           //     for (int i = 0; i < N; i++) {
           //         outputKey.set(i + "," + indicesAndValue[2]);
           //         outputValue.set("B," + indicesAndValue[1] + "," + indicesAndValue[3]);
           //         context.write(outputKey, outputValue);
           //     }
           // }
        //}
    }
} 
    public static class Reduce extends Reducer<Text, Text, Text, Text> {
        public void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            String[] value;
            HashMap<Integer, Float> hashA = new HashMap<Integer, Float>();
            HashMap<Integer, Float> hashB = new HashMap<Integer, Float>();
            for (Text val : values) {
                value = val.toString().split(",");
                if (value[0].equals("A")) {
                    hashA.put(Integer.parseInt(value[1]), Float.parseFloat(value[2]));
                } else {
                    hashB.put(Integer.parseInt(value[1]), Float.parseFloat(value[2]));
                }
            }
            int n = Integer.parseInt(context.getConfiguration().get("n"));
            float result = 0.0f;
            float a_ij;
            float b_jk;
            for (int j = 0; j < n; j++) {
                a_ij = hashA.containsKey(j) ? hashA.get(j) : 0.0f;
                b_jk = hashB.containsKey(j) ? hashB.get(j) : 0.0f;
                result += a_ij * b_jk;
            }
            if (result != 0.0f) {
                context.write(null, new Text(key.toString() + "," + Float.toString(result)));
            }
        }
    }
 
    public static void main(String[] args) throws Exception {

	 if (args.length < 2) {
		System.err.println("Usage : hadoop jar Mm.jar Mm matrices.in res.out"); 
		System.exit(0);
	}
	

        Configuration conf = new Configuration();
        // A is an L-rows, M-cols matrix
 	  // B is an M-rows, N-cols matrix.
        conf.set("L", "2");   
        conf.set("M", "5");
        conf.set("N", "3");
 
        Job job = new Job(conf, "MatrixMultiplication");
        job.setJarByClass(Mm.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);
 
        job.setMapperClass(Map.class);
        job.setReducerClass(Reduce.class);
 
        job.setInputFormatClass(TextInputFormat.class);
        job.setOutputFormatClass(TextOutputFormat.class);
 
        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
 
        job.waitForCompletion(true);
    }
}

