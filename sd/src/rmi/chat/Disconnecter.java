/**
 * Disconnecter
 */


public class Disconnecter extends Thread{
    private ChatService c;
    private int cli_id;

    public Disconnecter(ChatService c, int cli_id){
        this.c = c;
        this.cli_id = cli_id;    
    }

    public void run() {
        try {
            c.disconnect(cli_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

