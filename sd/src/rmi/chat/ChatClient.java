/**
 * ChatClient
 * 
 * The client of the Chat Service.
 * 
 * To improve a bit the dynamicity of message display from other clients,
 * a thread is created to periodically call the 'refresh()' method.
 * This could be further improved to avoid a message being typed to be
 * interrupted by an external message display. 
 * 
 *  S. Genaud (dec 2013)
 */

import java.rmi.* ; 
import java.net.MalformedURLException ; 
import java.io.*;
import java.util.Scanner;
import java.util.Date;

public class ChatClient {

private static String serviceName=new String("MyCoolChat");

	  public static void main(String [] args) {

		    int id=-1;
		    int mid=0;
		    ChatService c=null;

		    if (args.length != 1) {
				System.out.println("Usage : java Client <machine du Serveur>");
				System.exit(1);
		    }

		    try {
				try {
 					  System.out.println("About to connect to "+args[0]+"...");
					  // get a ref to the remote serveur object
					  c = (ChatService) Naming.lookup("//"+args[0]+"/"+serviceName);
					  id = c.connect();
				} catch (Exception e) {
					  System.out.println("Cannot connect to: " + e) ;
					  e.printStackTrace(System.out);
					  System.exit(1);
				}

				if (-1==id) {
						System.err.println("[Error] could not get registered to the server. No id returned.");
						System.exit(1);
				}
				System.out.println("Connected with id="+id+". Type \"/quit\" to quit.");
				// Manage SIGINT
				Runtime.getRuntime().addShutdownHook(new Disconnecter(c, id));
				// Thread to poll the messages
				Thread t = new Refresher(c, id);
				t.start();


				Scanner sc = new Scanner(System.in);
				String str = "";
				// iterate on message input at keyboard
				while(true) {
					  // This is our prompt
					  System.out.print("% "); 
					  str = sc.nextLine();
					  if(str.equals("/quit")) {
						    c.disconnect(id);
						    System.exit(0);
					  }
					  mid++;
					  Message m = new Message(str, id, mid, (new Date()).getTime());
					  c.send(m);
				}
		
		    } catch (Exception e) {
			System.out.println("Error: " + e) ;
			e.printStackTrace(System.out);
		    }
	  }
}

