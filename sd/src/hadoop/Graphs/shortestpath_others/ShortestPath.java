/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

//package org.apache.hadoop.examples;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.apache.hadoop.mapreduce.Counters;

import org.apache.log4j.Logger;
//import org.apache.commons.logging.Log;
//import org.apache.commons.logging.LogFactory;

/**
 * This is an example Hadoop Map/Reduce application. 
 * 
 * It inputs a map in adjacency list format, and performs a breadth-first search.
 * The input format is
 * ID   EDGES|DISTANCE|COLOR
 * where
 * ID = the unique identifier for a node (assumed to be an int here)
 * EDGES = the list of edges emanating from the node (e.g. 3,8,9,12)
 * DISTANCE = the distance of the node from the source as known so far
 * COLOR = a simple status tracking field to keep track of when we're finished with a node
 * It assumes that the source node (the node from which to start the search) has
 * been marked with distance 0 and color GRAY in the original input.  All other
 * nodes will have input distance Integer.MAX_VALUE and color WHITE.
 */
public class ShortestPath {


	  private static final Logger LOG = Logger.getLogger(ShortestPath.class);
	
	  static enum Iterations {
		    GrayTokens
	  }

	  /**
	   * Definition of the Map Class
	   *
	   * Nodes that are Color.WHITE or Color.BLACK are emitted, as is. For every
	   * edge of a Color.GRAY node, we emit a new Node with distance incremented by
	   * one. The Color.GRAY node is then colored black and is also emitted.
	   */
	  public static class MyMap extends Mapper<LongWritable, Text, IntWritable, Text> {

		    public void map(LongWritable key, Text value, Context context) 
				 throws IOException, InterruptedException {

				System.out.println("Map executing for key [" + key.toString() + "] and value [" + value.toString()
						    + "]");

				Node node = new Node(value.toString());

				// For each GRAY node, emit each of the edges as a new node (also GRAY)
				if (node.getColor() == Node.Color.GRAY) {
					  for (int v : node.getEdges()) {
						    Node vnode = new Node(v);
						    vnode.setDistance(node.getDistance() + 1);
						    vnode.setColor(Node.Color.GRAY);
						    context.write(new IntWritable(vnode.getId()), vnode.getLine());
					  }
					  // We're done with this node now, color it BLACK
					  node.setColor(Node.Color.BLACK);
				}

				// No matter what, we emit the input node
				// If the node came into this method GRAY, it will be output as BLACK
				context.write(new IntWritable(node.getId()), node.getLine());

				LOG.info("Map outputting for key[" + node.getId() + "] and value [" + node.getLine() + "]");
				System.out.println("Map outputting for key[" + node.getId() + "] and value [" + node.getLine() + "]");

		    }
	  }

	  /**
	   * Definition of the Reducer Class
	   *
	   * The reducer just emits the sum of the input values.
	   */
	  public static class MyReduce extends Reducer<IntWritable, Text, IntWritable, Text>  {

		    /**
		     * Make a new node which combines all information for this single node id.
		     * The new node should have 
		     * - The full list of edges 
		     * - The minimum distance 
		     * - The darkest Color
		     */
		    public void reduce(IntWritable key, Iterator<Text> values, Context context) 
					  //OutputCollector<IntWritable, Text> output, Reporter reporter) throws IOException {
 throws IOException, InterruptedException {

					 System.err.println("Reduce executing for input key [" + key.toString() + "]");
					 LOG.info("Reduce executing for input key [" + key.toString() + "]");

					 List<Integer> edges = null;
					 int distance = Integer.MAX_VALUE;
					 Node.Color color = Node.Color.WHITE;

					 Text value = values.next();

					 Node u = new Node(key.get() + "\t" + value.toString());

					 // One (and only one) copy of the node will be the fully expanded
					 // version, which includes the edges
					 if (u.getEdges().size() > 0) {
						   edges = u.getEdges();
					 }

					 // Save the minimum distance
					 if (u.getDistance() < distance) {
						   distance = u.getDistance();
					 }

					 // Save the darkest color
					 if (u.getColor().ordinal() > color.ordinal()) {
						   color = u.getColor();
					 }
					 // maintains the number of GRAY nodes remaining, used by the job driver
					 // to determine if more iterations should be taken (while there are GRAY nodes)
					 if (u.getColor() == Node.Color.GRAY) {
						   System.out.println("Incrementing counter for Node key [" + key.toString() + "]");
						   context.getCounter(Iterations.GrayTokens).increment(1L);
					 }
					 Node n = new Node(key.get());
					 n.setDistance(distance);
					 n.setEdges(edges);
					 n.setColor(color);
					 //output.collect(key, new Text(n.getLine()));
					 context.write(key, new Text(n.getLine()));
					 System.out.println("Reduce outputting final key [" + key + "] and value [" + n.getLine() + "]");
			     }
	  }


	  /* 
	   * Iterate. Settings and job spawn at each iteration.
 	   * 
         * @return true if all nodes are black, false otherwise, i.e there are more nodes to reduce
	   */
	  private static boolean iterate(int iterationCount, String [] args) throws Exception {

		    Configuration conf = new Configuration();
		    String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
		    if (otherArgs.length != 2) {
				System.err.println("Usage: Program <in> <out>");
				System.exit(2);
		    }
		    // define classes used for that job
		    Job job = new Job(conf, "ShortestPath with Counters");
		    job.setJarByClass(ShortestPath.class);
		    job.setMapperClass(MyMap.class);
		    job.setReducerClass(MyReduce.class);

		    // the keys are the unique identifiers for a Node (ints in this case).
		    job.setOutputKeyClass(IntWritable.class);
		    // the values are the string representation of a Node
		    job.setOutputValueClass(Text.class);

		    String input;
		    if (iterationCount == 0)
				input = "input-graph";
		    else
				input = "output-graph-" + iterationCount;

		    String output = "output-graph-" + (iterationCount + 1);

		    // define news intput/output filenames at each iteration 
		    FileInputFormat.setInputPaths(job, new Path(input));
		    FileOutputFormat.setOutputPath(job, new Path(output));
		    LOG.info("Job configured: in="+input+"/out="+output);
		    if (!job.waitForCompletion(true)) {
				throw new Exception("Job failed");
		    }
		    // Check how many Gray nodes have been counted by reducers
		    Counters jobCntrs = job.getCounters();
		    long leftNodes = jobCntrs.findCounter(Iterations.GrayTokens).getValue();
		    // If  then there are GRAY nodes remaining, iterate to process them.
		    LOG.info("Job finished: leftNodes="+leftNodes);
		    if (leftNodes > 0) 
				return(false);
		    else 
		    		return(true);
	  }


	  /*
	   * Main.
	   *
	   */
	  public static void main(String[] args) {

		    boolean isFinished = false;
		    int iterationCount = 0;
		    LOG.info("Program starts ...");
		    do {
				System.out.println("-- Iteration "+iterationCount+" ----");
				try {
					  isFinished = iterate(iterationCount, args);
				}
				catch(Exception e) {
					  e.printStackTrace();
				}
				iterationCount++;
		    }
		    while (!isFinished);
	  }
	  }
