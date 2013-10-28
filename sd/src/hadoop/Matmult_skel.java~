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
            String line = value.toString();
            
            // matrixNum = 0 : matrice A
            // matrixNum = 1 : matrice B
            int matrixNum = 0;

            for (String mat : line.split(":")) { // // Split the two matrices. For each matrix ...
                
                // keep only inner values of the matrix and ...
                mat = mat.substring(mat.indexOf("[[");,mat.lastIndexOf("]]"););
                // and split lines as an array of form: [line1,line2,...,line I]
                String[] tab = mat.split("][");
                // => tab.length is the number of lines
                

                // For each line ...
                int num_line = 0;
                for (String mat_line : tab)
                {
                    num_line++;
                    // split the line i as an array of elements [a_{i,0}; .. ; a_{i,M}]
                    String[] elements = mat_line.split(",");
                    // => elements.length is the nuber of columns

                    for( int i = 1 ; i <= elements.length; i++)
                    {
                        // Iterate over the line for matrix A, and over the column for matrix B.
                    	// Therefore, compute 'size' the number of elements to iterate over.
                        int size = (matrixNum == 0) ? tab.length : elements.length;

                        for( int j = 1 ; j <= size ; j++)
                        {
                            
                        	// Generate a (key,val) pair for each element.
                        	// key = coords of the resulting matrix C element
                        	// val =  element A_i,j  or B_i,j
                        	// As C_x,y needs A_x,. and B .,y   when an A_x,. is scanned, emit it as C_x,. key
                            //
                        	
                            if(matrixNum==0)
                            {
                                key.set(num_line+"."+j+"."+i);
                            }
                            else
                            {
                                key.set(j+"."+i+"."+num_line);
                            }
                            // Add to the output, le couple (clé,valeur)
                            output.collect(key,new IntWritable(
                                Integer.parseInt(elements[i-1])));
                        }
                    } 
                }
                matrixNum=1;
            }
        }
    }

    // Reduce 1 permet de faire la multiplication dans les valeurs qui servent
    // pour le calcul du même élément de la matrice finale.
    public static class Reduce1 extends MapReduceBase 
        implements Reducer<Text, IntWritable, Text, IntWritable> {

        // la clé qui représente la façon dont les données vont se combiner
        private Text word = new Text();

        public void reduce(Text key, Iterator<IntWritable> values, 
            OutputCollector<Text, IntWritable> output, Reporter reporter) 
            throws IOException {
            // initialisation du produit 
            int prod = 1;

            // Pour chaque valeur ayant la même clé, on les multiplie.
            // A ce stade c'est toujours une multiplication de deux valeurs.
            while(values.hasNext()) {
                prod *= values.next().get();
            }

            // Récupération de la clé pour la modifier. On passe d'une
            // nomenclature en X.X.y ou X.X correspondent à la position de
            // résultat dans la matrice finale et y le numéro de la donnée.
            // Ce numéro n'est plus nécessaire pour la suite, on le supprime
            // donc.
            String str = key.toString();
            int index = str.lastIndexOf(".");
            word.set(str.substring(0,index));

            // La nouvelle clé et sa valeur sont ajouté à la liste d'output
            output.collect(word, new IntWritable(prod));
        }
    }

    // Reduce 2 : permet de faire la somme des éléments entrant dans la
    // composition d'une valeur de la matrice final.
    public static class Reduce2 extends MapReduceBase 
        implements Reducer<Text, IntWritable, Text, IntWritable> {

        public void reduce(Text key, Iterator<IntWritable> values, 
            OutputCollector<Text, IntWritable> output, Reporter reporter) 
            throws IOException {

            // Chaque élément ayant la même clé sont sommés afin d'obtenir la
            // valeur d'un élément de la matrice finale
            int sum = 0;
            while(values.hasNext()) {
                sum += values.next().get();
            }

            output.collect(key, new IntWritable(sum));
        }
    }

    // Fonction principale permettant d'appeler les différents map et reduce
    // nécessaire au programme.
    public static void main(String[] args) throws Exception {
        JobConf conf = new JobConf(prodMat.class);
        conf.setJobName("prodmat");
    
        conf.setOutputKeyClass(Text.class);
        conf.setOutputValueClass(IntWritable.class);
    
        conf.setMapperClass(Map1.class);
        conf.setCombinerClass(Reduce1.class);
        conf.setReducerClass(Reduce2.class);
    
        conf.setInputFormat(TextInputFormat.class);
        conf.setOutputFormat(TextOutputFormat.class);

        FileInputFormat.setInputPaths(conf, new Path(args[0]));
        FileOutputFormat.setOutputPath(conf, new Path(args[1]));

        JobClient.runJob(conf);
    }
}
