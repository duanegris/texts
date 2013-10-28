import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.net.InetAddress.* ;
import java.net.* ;

public class AccountManagerImpl 
    extends UnicastRemoteObject
    implements Operation  {

    public OperationImpl () throws RemoteException 
    { 
      super(); 
    };

    public int withdraw() throws RemoteException { 
	return( a + b) ;
    }
}
