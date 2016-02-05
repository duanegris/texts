import java.rmi.*;
import java.rmi.registry.*;
import java.net.*;

public class ChatServer {
	private  static String serviceName="MycoolChat";

   public static void main(String [] args) {
	     // Lancer le RMIregistry par programme (s'arrete avec le serveur)
	     try {
			 LocateRegistry.createRegistry(1099);
			 System.out.println("RMI registry pret.");
	     } catch (Exception e) {
			 System.out.println("RMI registry: erreur demarrage. Deja lance ?");
	     }
	     // Enregistrer service dans le registry
	     try {
			 ChatServiceImpl s  = new ChatServiceImpl ();
			 Naming.rebind("rmi://localhost/"+serviceName,s) ;
			 System.out.println("ChatService up and running. Id in remiregistry is ["+serviceName+"]"); 
	     }
	     catch (RemoteException re) { System.out.println(re) ; }
	     catch (MalformedURLException e) { System.out.println(e) ; }
   }
}
