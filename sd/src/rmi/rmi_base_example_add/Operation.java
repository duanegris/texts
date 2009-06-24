import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface Operation extends Remote {
    public int addition(int a, int b) throws RemoteException ;
}
