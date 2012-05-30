import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Iterator;	
import java.util.Vector;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/*
 Probleme rencontré : 
 J'ai rencontré un souci avec java lorsque je lance la commande suivante de hadoop 
 $ hadoop -jar 
 j'obtiens l'erreur suivante : /usr/bin/hadoop: ligne 360: /home/thomas/School/Masters/Master2/M2_ILC/Courses/Parallelisme_Genaud/ /usr/lib/jvm/java-6-openjdk/jre./bin/java: Aucun fichier ou dossier de ce type

Donc je n'ai pas pu terminer comme je le voulais le tp. Le deuxième reduce n'est pas tout à fait correct mais marche très bien. 
Si vous avez une idée de ce qui pourrait causer ce problème, je vous prie de m'en informer (j'ai cherché partout sur google etc mais je n'ai rien trouvé).

Thomas.
  
 */



public class DegreSommet {

	
//Mapper
  public static class SommetMapper 
       extends Mapper<Object, Text, Text, Text>{
    
    
    private Text word = new Text();
    private Text word2 = new Text();
    private Text word3 = new Text();
      
    public void map(Object key, Text value, Context context
                    ) throws IOException, InterruptedException {
      StringTokenizer itr = new StringTokenizer(value.toString());
      
      while (itr.hasMoreTokens()) {
    	String next = itr.nextToken();
        word.set(next);
        
        word2.set("" + next.charAt(1));
        word3.set("" + next.charAt(3));
        
        System.out.println("map "+next.charAt(1) + " et " + next.charAt(3));
        
        //On divise chaque relation en deux parties : exemple [A, B] donne A [A,B] et B [A,B]
        context.write(word2, word);
        
        context.write(word3, word);
        
      }
    }
  }
  
  
  //Reduce 1
  public static class IntSumReducer 
       extends Reducer<Text,Text,Text,Text> {
	  
    private IntWritable result = new IntWritable();
    private Text res = new Text();

    public void reduce(Text key, Iterable <Text> values, 
                       Context context
                       ) throws IOException, InterruptedException {
      
    
      int sum = 0;
      Vector <String> newValues = new Vector();
      
      
      // pour chaque clé qui a la meme valeur on augment sum
      for (Text val : values) {
    	  System.out.println("cle:"+key.toString()+"  valeur1 : "+val.toString()+ " sum vaut "+ sum);
    	  newValues.add(sum, val.toString());
    	  sum++;
    	  
      }
      
      
      for(int i = 0; i < sum ; i ++){
    	  
    	  //On compte le numbre de fois que la relation apparait pour la meme clé
    	  // et on reecrit la relation
    	  res.set("d(" +key.toString() + ") -> " + sum);
    	  Text wordb = new Text();
    	  wordb.set(newValues.get(i));
    	  context.write(wordb , res);
      }
    }
  }
  

  
  //Reduce 2
public static class IntSumReducer2 
	extends Reducer<Text,Text,Text,Text> {
	
	//On utilise un hashmap pour stocker chaque relation déjà rencontrée et y ajouter une nouvelle valeur
	// J'aurais pu faire une en utilisant un tableau à deux dimensions
	// mais je n'ai pas pu continuer à cause de l'erreur mentionnée dans le commentaire du rendu de mon travail.
	
	
	// En tout cas le but c'est d'utiliser un tableau pour rassembler les deux valeurs de chaque relation.
	
	Map<String, String> hashMap = new HashMap<String, String>();
	
	public void reduce(Text key, Iterable <Text> values, 
            Context context
            ) throws IOException, InterruptedException {
		
		
	 int sum = 0;
	 String res = "";
	 
	 for (Text val : values) {
		 if(hashMap.containsKey(key.toString())){
			 hashMap.put(key.toString(), hashMap.get(key.toString())+", "+ val.toString());
		 }else{
			 hashMap.put(key.toString(), val.toString());
		 }
		 
//		 context.write(key, val);
		 
     }
	 
//	 
//	 for (String mapKey : hashMap.keySet()) {
//		
//		 Text result = new Text();
//		 Text cle = new Text();
//		 cle.set(mapkey);
//		 result.set(hashMap.get(mapkey));
//		 context.write(cle, result);
//	 }
	 
     Iterator it = hashMap.keySet().iterator();
	 while (it.hasNext()) {
         String mapkey = (String)(it.next());
         
         Text result = new Text();
		 Text cle = new Text();
		 cle.set(mapkey);
		 result.set(hashMap.get(mapkey));
		 context.write(cle, result);
         }
	 //}
//	 
	}
}

  public static void main(String[] args) throws Exception {
    Configuration conf = new Configuration();
    
    Job job = new Job(conf, "degre sommet");
    job.setJarByClass(DegreSommet.class);
    
    //Mapper une première fois
    job.setMapperClass(SommetMapper.class);
    
    //Reduce une première fois
    job.setCombinerClass(IntSumReducer.class);
    
    //Reduce une seconde fois
    job.setReducerClass(IntSumReducer2.class);
    
    job.setOutputKeyClass(Text.class);
    job.setOutputValueClass(Text.class);
    FileInputFormat.addInputPath(job, new Path(args[0]));
    FileOutputFormat.setOutputPath(job, new Path(args[1]));
    System.exit(job.waitForCompletion(true) ? 0 : 1);
  }
}
