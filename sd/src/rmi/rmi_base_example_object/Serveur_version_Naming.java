import java.rmi.*;
import java.net.*;

public class Serveur {
   public static void main(String [] args) {
       try {
	   OperationImpl une_op = new OperationImpl ();
	   Naming.rebind("rmi://"+args[0]+"/Operation",une_op) ;
	   System.out.println("Serveur pret"); 
       }
       catch (RemoteException re) { System.out.println(re) ; }
	 catch (MalformedURLException e) { System.out.println(e) ; }
  }
}
