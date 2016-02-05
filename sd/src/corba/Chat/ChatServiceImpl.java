/**
 * ChatServiceImpl
 * 
 * The implementation for methods of the Chat Service.
 * To be improved: 
 * - an infinite number of messages should be possible
 * 
 *  S. Genaud (dec 2013)
 */


import ChatService.*;
import java.net.InetAddress.* ;
import java.net.* ;
import java.util.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;


public class ChatServiceImpl extends ChatPOA {
 
	 private final int MAX_CLIENT = 128;
	  // list of connected ids: index in array is client id, 
	  // ids[i] contains connection date (hence the long type), 0 means disconected.
	  private long [] ids; 
	  // all messages 
      private ArrayList<Message> messages;

	  private long [] lastSeen;
	  private ORB orb;

	  
	  // CORBA specific. We need to have this.
	  public void setORB(ORB orb_val) {
	      this.orb = orb_val;
	  }

	  // Constructor
	  public ChatServiceImpl()  {
		    super();
		    ids      = new long[MAX_CLIENT];
		    messages = new ArrayList<ChatService.Message>(256);
		    lastSeen = new long[MAX_CLIENT];
	  }

	  
	  /**
	   * connect 
	   * Register the connecting client and returns an id.
	   * Return the first free id from ids array in the range [0..lastClientId].
	   * ids[i]==-1 if i is a free id,   
	   * @return the first free id or -1 if MAX_CLIENTS reached
	   **/
	  public int connect() {
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

	  
	/** 
	 * send 
	 * A message is received from the client, store it in the global array. That's all.
	 * @param msg the message received
	 */
	public void send(ChatService.Message msg) {
		   messages.add( msg );	
	}
	
	
    /**
     * resfresh
     * Consider t_0 the date at which client cliId called this method last time, and t_1 the method call occuring now,
     * extract the list of messages aged between t_0 and t_1 and that are not from cliId. t_0 is implemented
     * through the lastSeen[] array.
     * @param cliId the id of the client who requests the refresh
     * @param t_1 the timestamp when the request was made
     * @return the messages that this client should see as new messages (those between 
     **/
	public  ChatService.Message[] refresh(int cliId, long t_1)  {
	
		ArrayList<Message> mlist = new ArrayList<Message>();

        for(Message m : this.messages) {
        	if(m.timestamp > lastSeen[cliId] && m.cli_id != cliId)
                  mlist.add( m );
         }
         //memorize this last visit (this t_0 in comments ahead)
         lastSeen[cliId] = t_1;
		 return( mlist.toArray(new ChatService.Message[mlist.size()]) );  // toArray() makes the conversion from ArrayList to Arrray
     }

	  
	/**
	 * disconnect
	 * @param id the id of the client disconnecting
	 */
	  public void disconnect(int id)  {
		    ids[id] = 0;
		    System.out.println("Disconnect client " + id);
	  }
}
