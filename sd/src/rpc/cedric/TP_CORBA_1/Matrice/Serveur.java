import java.io.*;
import org.omg.CORBA.*;

public class Serveur {

    public static void main(String[] args) {
    try {
        //init ORB

        ORB orb = ORB.init( args, null );
        
        OpMatriceImpl myobj = new OpMatriceImpl();        

        String ior = orb.object_to_string( myobj );
        
	FileOutputStream file = new FileOutputStream(iorfile.value);
	PrintWriter out = new PrintWriter(file);
	out.println(ior);out.flush();
	file.close();

//        System.out.println( ior );
        
        orb.run();
    }
    catch( org.omg.CORBA.SystemException ex ) {  ex.printStackTrace(); }
    catch( java.io.IOException ex) {  ex.printStackTrace(); }
    }
}
