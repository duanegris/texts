import java.rmi.*;
import java.net.*;

public class Serveur {

   public static void main(String [] args) {
	     // Lancer le RMIregistry par programme (s'arrete avec le serveur)
	     try {
			 java.rmi.registry.LocateRegistry.createRegistry(1099);
			 System.out.println("RMI registry pret.");
	     } catch (Exception e) {
			 System.out.println("RMI registry: erreur demarrage. Deja lance ?");
	     }
	     // Enregistrer service dans le registry
	     try {
			 OperationImpl une_op = new OperationImpl ();
			 Naming.rebind("rmi://localhost/Operation",une_op) ;
			 System.out.println("Serveur pret"); 
	     }
	     catch (RemoteException re) { System.out.println(re) ; }
	     catch (MalformedURLException e) { System.out.println(e) ; }
   }
}
