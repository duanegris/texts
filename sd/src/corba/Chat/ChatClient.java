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

import ChatService.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.*;

public class ChatClient {

	  static Chat c;

	  public static void main(String [] args) {
		    int id=-1;
		    int mid=0;

		    if (args.length != 3) {
				System.out.println("Usage : java ChatClient -ORBInitialPort xxx <machine du Serveur>");
				System.exit(1);
		    }

		    try {
				// create and initialize the ORB
				ORB orb = ORB.init(args, null);

				// get the root naming context
				org.omg.CORBA.Object objRef = 
					  orb.resolve_initial_references("NameService");
				// Use NamingContextExt instead of NamingContext. This is 
				// part of the Interoperable naming Service.  
				NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

				// resolve the Object Reference in Naming
				String name = "Chat";
				c = ChatHelper.narrow(ncRef.resolve_str(name));

				id = c.connect();
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
