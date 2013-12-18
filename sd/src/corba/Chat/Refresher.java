/**
 * S. Genaud
 */
import ChatService.*;
import java.util.ArrayList;
import java.util.Date;

public class Refresher extends Thread {
    private Chat c;
    private int cli_id;
  
   /**
    * Constructor
    **/
    public Refresher(Chat c, int cli_id) {
        this.c = c;
        this.cli_id = cli_id;
    }
    
    
    /**
     * display
     * Helper function in charge of output
     * param mlist the list of messages to display
     **/
    public void display (ChatService.Message[] mlist) {
    // in RMI, was: public void display (ArrayList<Message> mlist) {
    	char ESC=27; //
    	String red="[0;31;40m";
    	String underlinered="[3;31;40m";
    	String normal="[0;37;40m";
    	Message m; // shortcut
    	for (int i=0;i<mlist.length;i++) {
    	// in RMI, an ArrayList was used. Was : for (Message m : mlist) {
    		m = mlist[i];
    		if (m.msg.length()>0)
    			System.out.println("-> User "+m.cli_id+" says: "+ESC+ underlinered + m.msg+ESC+normal);
    	}	
    }
    
    
    /**
     * The thread start
     **/
    public void run() {
    	ChatService.Message[] newMsg;
        // in RMI, was : ArrayList<Message> newMsg;
        while (true) {
            try {
                sleep(1000);
                newMsg = c.refresh(this.cli_id, (new Date()).getTime());
                if (newMsg.length > 0) {
                // in RMI, was : if (newMsg.size() > 0) {
                	display( newMsg );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
         }
    }
}

