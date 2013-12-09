/**
 * ChatServiceImpl
 * 
 * The implementation for methods of the Chat Service.
 * To be improved: 
 * - an infinite number of messages should be possible
 * 
 *  S. Genaud (dec 2013)
 */
import java.rmi.server.UnicastRemoteObject ;
import java.rmi.RemoteException ;
import java.net.InetAddress.* ;
import java.net.* ;
import java.util.*;


public class ChatServiceImpl extends UnicastRemoteObject 
	implements ChatService {


	 private final int MAX_CLIENT = 128;
	  // list of connected ids: index in array is client id, 
	  // ids[i] contains connection date (hence the long type), 0 means disconected.
	  private long [] ids = new long[MAX_CLIENT];
	  private ArrayList<Message> messages = new ArrayList<Message>(256);
	  private long [] lastSeen = new long[MAX_CLIENT];

	  public ChatServiceImpl() throws RemoteException {
		    super();
	  }

	  /*
	   * connect 
	   * Register the connecting client and returns an id.
	   * Return the first free id from ids array in the range [0..lastClientId].
	   * ids[i]==-1 if i is a free id,   
	   * @return the first free id or -1 if MAX_CLIENTS reached
	   **/
	  public int connect() throws RemoteException {
		    int i=0;
		    // search for first free slot for the new client
		    while ( ids[i]>0 &&  i<MAX_CLIENT) {
		    	i++;
		    }
		    if (i < MAX_CLIENT) {
				  // means we can reuse this slot
				  ids[i]= (new Date()).getTime();
				  System.out.println("New client registered with id: " + i);
			}
		    else 
		    	i=-1;
		    return(i);
	  }

	  
	  
	public void send(Message msg) throws RemoteException {
		   // uncomment below for debug purpose
		   //System.out.println("Added message ["+msg.getContent()+"] from (cli="+msg.getCliId()+",mid="+msg.getId()+")@"+msg.getTimestamp());
		   messages.add(msg);
	}
	
	
    /**
     * resfresh
     * Consider t_0 the date at which client cliId called this method last time, and t_1 the method call occuring now,
     * extract the list of messages aged between t_0 and t_1 and that are not from cliId. 
     **/
	public  ArrayList<Message> refresh(int cliId, long t_1) throws RemoteException {
	ArrayList<Message> mlist = new ArrayList<Message>();
	
		    for(Message m : this.messages) {
				if(m.getTimestamp() > lastSeen[cliId] && m.getCliId() != cliId)
					  mlist.add( m );				
		    }
		    //memorize this last visit (this t_0 in comments ahead)
	        lastSeen[cliId] = t_1;
		    return( mlist );
	  }
       

	  public void disconnect(int id) throws RemoteException {
		    ids[id] = 0;
		    System.out.println("Disconnect client " + id);
	  }
}
