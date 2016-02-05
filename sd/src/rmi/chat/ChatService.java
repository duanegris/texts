import java.rmi.Remote; 
import java.rmi.RemoteException; 
import java.util.ArrayList;


public interface ChatService extends Remote {

	  public int connect() throws RemoteException ;

	  public void disconnect(int id) throws RemoteException ;

	  public void send(Message m) throws RemoteException ;

	  /**
	   * resfresh
	   * Extract a list of messages older than the timestamp passed as 
         * argument that are not from client cliId 
	   **/
	  public  ArrayList<Message> refresh(int cliId, long timestamp) throws RemoteException ;
}
