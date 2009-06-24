import java.util.Enumeration;
import java.util.Date;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.Advertisement;
import net.jxta.document.MimeMediaType;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.protocol.DiscoveryResponseMsg;
import net.jxta.protocol.PeerAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.OutputPipeEvent;
import net.jxta.pipe.OutputPipeListener;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.exception.PeerGroupException;
import net.jxta.impl.endpoint.WireFormatMessage;
import net.jxta.impl.endpoint.WireFormatMessageFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;




/**
 *  This is a quick and simple example to primarly illustrate how to start project jxta
 *  from an application, and how to create a DiscoveryListener interface
 *  This is meant as an on going effort, if you can add to the
 *  example to help others understand, and integrate project jxta,
 *  feel free to do so, please refer to the "todo" section in README .
 */

public class Recepteur implements Runnable, DiscoveryListener,PipeMsgListener {


    static PeerGroup platformGroup = null;
    static PeerGroup netPeerGroup  = null;
    static PeerGroupAdvertisement groupAdvertisement = null;
    private final static String SenderMessage = "PipeStephMsg";


    private DiscoveryService discovery;
    PeerAdvertisement padv;

    private void startJxta() {
        try {
            netPeerGroup = PeerGroupFactory.newNetPeerGroup();

            // uncomment the following line if you want to start the app defined
            // the NetPeerGroup Advertisement (by default it's the shell)
            // in our case we want use jxta directly.

            // netPeerGroup.startApp(null);


        }
        catch ( PeerGroupException e) {
            // could not instantiate the group, print the stack and exit
            System.out.println("** fatal error : group creation failure");
            e.printStackTrace();
            System.exit(1);
        }

        // obtain our group advertisement
        groupAdvertisement = netPeerGroup.getPeerGroupAdvertisement();

        // Let's get the discovery service we're going to use this
        // one later on
        discovery = netPeerGroup.getDiscoveryService();
    }

    /**
     * This thread basically loops forever discovering adv
     * every minute
     */

    public void run() {
        String peer = null;
        try {
            // Add a DiscoveryListener for *all* DiscoveryResponse events
		// discovery.addDiscoveryListener(this);
            while (true) {
                // wait a bit before sending a discovery message
                try {
                    Thread.sleep(60 * 1000);
                } catch(Exception e) {}
                System.out.println("Sending a Discovery Message");
                // look for any peer
                discovery.getRemoteAdvertisements(
                    null,
                    DiscoveryService.ADV,
                    "Name",
                    "PipeSteph",
                    1,
                    this);   // <-- Add listener to listen only for this discovery
          }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * by implementing DiscoveryListener we must define this method
     * to deal to discovery responses
     */

    public void discoveryEvent(DiscoveryEvent ev) {

        DiscoveryResponseMsg res = ev.getResponse();
        Advertisement ad=null;
        Enumeration ads = res.getAdvertisements();
        if (ads != null ) {
            while (ads.hasMoreElements()) {
                 System.out.println("-------------------------------------------------------------");
                ad = (Advertisement) ads.nextElement();
                System.out.println (ad);
            }
        }

    }

    /**
     * La classe implemente PipeMsgListener => pipeMsgEvent est le listener defini
     * pour un evenement sur le pipe.
     */
    public void pipeMsgEvent(PipeMsgEvent event) {

        Message msg=null;
        try {
            // grab the message from the event
            msg = event.getMessage();
            if (msg == null) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // get all the message elements
        Message.ElementIterator en = msg.getMessageElements();
        if (!en.hasNext()) {
            return;
        }

        // get the message element named SenderMessage
        MessageElement msgElement = msg.getMessageElement(null, SenderMessage);
        // Get message
        if (msgElement.toString() == null) {
            System.out.println("null msg received");
        } else {
            Date date = new Date(System.currentTimeMillis());
            System.out.println("Message received at :"+ date.toString());
            System.out.println("Message  created at :"+ msgElement.toString());
        }
    }

    static public void main(String args[]) {
        Recepteur myapp  = new Recepteur();
        myapp.startJxta();
        myapp.run();
    }

}
