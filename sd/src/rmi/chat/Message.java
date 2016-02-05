/**
* Message.java 
* Information held for a message. 
*/

import java.io.Serializable;


public class Message implements Serializable {

  public int cli_id = 0;
  public int msg_id = 0;
  public String msg = null;
  private long timestamp;

  // constructor
  public Message (String _msg, int _cli_id, int _msg_id, long _timestamp)
  {
    cli_id = _cli_id;
    msg_id = _msg_id;
    msg = _msg;
    timestamp = _timestamp;
  } 

  public int getId( ) {
	    return(this.msg_id);
  }
  public String  getContent( ) {
	    return(this.msg);
  }
  public int getCliId( ) {
	    return(this.cli_id);
  }

  public long getTimestamp() {
	return(this.timestamp);
}

} // class Message
