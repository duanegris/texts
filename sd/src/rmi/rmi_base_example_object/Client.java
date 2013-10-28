import java.rmi.* ; 
import java.net.MalformedURLException ; 
import java.io.*;

public class Client {
    public static void main(String [] args) {
	if (args.length != 1) 
	    System.out.println("Usage : java Client <machine du Serveur>");
	else {
	    try {
		Operation o =(Operation) Naming.lookup("//"+args[0]+"/Operation");
	      System.out.println("Client : demande 33 + 45 ? "); 
            int r = o.addition( 33 , 45 );
	     System.out.println("Le serveur a calcule : a+b="+ r ); 
	    }

	    catch (NotBoundException re) { System.out.println(re) ; }
	    catch (RemoteException re) { System.out.println(re) ; }
	    catch (MalformedURLException e) { System.out.println(e) ; }
	}
    }
}
