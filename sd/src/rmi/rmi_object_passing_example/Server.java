import java.rmi.*;
import java.net.*;

public class Server {
	  public static void main(String [] args) {
		    if (System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
		    }
		    // Lancer le RMIregistry par programme (s'arrete avec le serveur)
		    try {
				java.rmi.registry.LocateRegistry.createRegistry(1099);
				System.out.println("RMI registry pret.");
		    } catch (Exception e) {
				System.out.println("RMI registry: erreur demarrage. Deja lance ?");
		    }
		    // Enregistrer service dans le registry

		    try {
				ComputeImpl engine1 = new ComputeImpl ();
				Naming.rebind("rmi://localhost/Compute",engine1) ;
				System.out.println("Objet Compute enregistré dans le registry.");
				System.out.println("Serveur pret.");
		    }
		    catch (RemoteException re) { System.out.println(re) ; }
		    catch (MalformedURLException e) { System.out.println(e) ; }
	  }
}
