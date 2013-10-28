import java.io.*;
import org.omg.CORBA.*;

public class Client {

    static public void afficheMatrice (int [][] mat) {
    for (int i = 0; i < mat.length; ++i) { 
        for (int j=0; j < mat[0].length; ++j) { 
        System.out.print(mat[i][j]+ " ");
        }
        System.out.println();
    }
    }

    public static void main( String args[] )  {
        if( args.length != 0 ) {
        System.out.println( "Usage: java Client" );
            System.exit( 1 );
        }

    // initialiser l'ORB.
    
    try {
        ORB orb = ORB.init( args, null );
        System.out.println( "0) ORB initialise'");

	String  ior;
	FileReader file = new FileReader(iorfile.value);
	BufferedReader in = new BufferedReader(file);
	ior = in.readLine();
	file.close();
//        ior = args[0];
        System.out.println( "1) IOR lue : " + ior );

        org.omg.CORBA.Object obj = 
        orb.string_to_object(ior);
        OpMatrice service = OpMatriceHelper.narrow(obj);
        System.out.println("2) Reference obtenue a partir de l'IOR");

        int[][] a = { {1, 0, 0}, {0, 2, 0}, {0, 0, 3}, {0, 0, 4} };
        int[][] b = { {1, 2, 3}, {1, 2, 3}, {1, 2, 3} };
        int res [][] = new int [3][3];
        res = service.addMat(a,b);
        afficheMatrice(res);

    }
    catch( org.omg.CORBA.SystemException ex ) {  ex.printStackTrace(); }
    catch( java.io.IOException ex) {  ex.printStackTrace(); }
    }
}
