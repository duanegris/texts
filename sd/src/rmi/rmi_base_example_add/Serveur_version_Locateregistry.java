import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Serveur {

	 public static void main( String [] args) {

       try {
	   OperationImpl une_op = new OperationImpl ();
	   Registry registry;
	   // pour lancer dynamiquement le registre
	   // (au lieu de lancer "rmiregistry &" ,
	   // decommenter la ligne suivante :
	   // registry = LocateRegistry.createRegistry(1099);
         registry = LocateRegistry.getRegistry();
	   registry.bind("Operation",une_op); 
	   System.out.println("Serveur pret"); 
	   
       }
       catch (Exception e) { System.out.println(e) ; }
  }
}
