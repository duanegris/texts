/**
 * A 
 * S. Gznaud
 */

import java.util.ArrayList;
import java.util.Date;

public class Refresher extends Thread {
    private ChatService c;
    private int cli_id;

  
   /**
    * Constructor
    **/
    public Refresher(ChatService c, int cli_id) {
        this.c = c;
        this.cli_id = cli_id;
    }
    
    
    /**
     * display
     * Helper function in charge of output
     * param mlist the list of messages to display
     **/
    public void display (ArrayList<Message> mlist) {
    	char ESC=27; //
    	String red="[0;31;40m";
    	String underlinered="[3;31;40m";
    	String normal="[0;37;40m";
    	for (Message m : mlist) {
    		if (m.getContent().length()>0)
    			System.out.println("-> User "+m.getCliId()+" says: "+ESC+ underlinered + m.getContent()+ESC+normal);
    	}	
    }
    
    
    /**
     * The thread start
     **/
    public void run() {
        ArrayList<Message> newMsg;
        while(true) {
            try {
                sleep(1000);
                newMsg = c.refresh(this.cli_id, (new Date()).getTime());
                if (newMsg.size() > 0) {
                	display( newMsg );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
         }
    }
}
