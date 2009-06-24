import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.net.InetAddress.* ;
import java.net.* ;

public class ComputeImpl extends UnicastRemoteObject implements Compute
{
    public ComputeImpl() throws RemoteException {
       /* constructeur de la classe mere UnicastRemoteObject */
       super();   
    }

    public Object executeTask(Task t) {
       /* les objets passes en arg. doivent fournir une methode execute() */
       return t.execute();   
    }
}
