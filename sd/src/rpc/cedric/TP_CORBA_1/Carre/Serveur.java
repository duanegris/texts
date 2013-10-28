import org.omg.CORBA.*;


public class Serveur {

    public static void main(String[] args) {
	try {
            //init ORB
	    org.omg.CORBA.ORB orb = ORB.init( args, null );
	    
	    ??? myobj = new ???();	    

	    String ior = orb.object_to_string( myobj );
	    
            System.out.println( ior );

      	    orb.run();
	}
	catch( org.omg.CORBA.SystemException ex ) { ex.printStackTrace();}
    }
}
