import java.rmi.Remote; 
import java.rmi.RemoteException; 

public interface AccountManager extends Remote {
    public int witdraw(Account a,float amount) throws RemoteException ;
}
