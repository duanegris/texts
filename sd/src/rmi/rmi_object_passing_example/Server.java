import java.rmi.*;
import java.net.*;

public class Server {
  public static void main(String [] args) {
     if (System.getSecurityManager() == null) {
         System.setSecurityManager(new SecurityManager());
     }
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
