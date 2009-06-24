import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.XMLDocument;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.OutputPipeEvent;
import net.jxta.pipe.OutputPipeListener;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.rendezvous.RendezVousService;
import net.jxta.rendezvous.RendezvousEvent;
import net.jxta.rendezvous.RendezvousListener;

/**
 *  This exapmle illustrates how to use the OutputPipeListener interface
 *
 */

public class Emetteur implements
                              Runnable,
                              OutputPipeListener,
                              RendezvousListener {

    static PeerGroup netPeerGroup = null;
    private final static String SenderMessage = "PipeStephMsg";
    private PipeService pipe;
    private DiscoveryService discovery;
    private PipeAdvertisement pipeAdv;
    private RendezVousService rendezvous;

    /**
     *  main
     *
     *@param  args  command line arguments
     */
    public static void main(String args[]) {
        Emetteur myapp = new Emetteur();
        myapp.startJxta();
        myapp.run();
    }

    /**
     *  the thread which creates (resolves) the output pipe
     *  and sends a message once it's resolved
     */

    public synchronized void run() {
        try {
            // create output pipe with asynchronously
            // Send out the first pipe resolve call
            System.out.println("Attempting to create a OutputPipe");
            pipe.createOutputPipe(pipeAdv, this);
            // send out a second pipe resolution after we connect
            // to a rendezvous
            if (!rendezvous.isConnectedToRendezVous()) {
                System.out.println("Waiting for Rendezvous Connection ..");
                try {
                    wait();
                    System.out.println("Connected to Rendezvous, attempting to create a OutputPipe ...");
                    pipe.createOutputPipe(pipeAdv, this);
                } catch (InterruptedException e) {
                    // got our notification
                    System.out.println(" interrupted.");
                }
            }
        } catch (IOException e) {
            System.out.println("OutputPipe creation failure");
            e.printStackTrace();
            System.exit(-1);
        }
    }


    /**
     *  by implementing OutputPipeListener we must define this method which
     *  is called when the output pipe is created
     *
     *@param  event  event object from which to get output pipe object
     */

    public void outputPipeEvent(OutputPipeEvent event) {

        System.out.println("--> Got an output pipe event");
        OutputPipe op = event.getOutputPipe();
        Message msg = null;

        try {
            System.out.println("Sending message");
            msg = new Message();
            Date date = new Date(System.currentTimeMillis());
            StringMessageElement sme = new StringMessageElement(SenderMessage, date.toString() , null);
            msg.addMessageElement(null, sme);
            op.send(msg);
        } catch (IOException e) {
            System.out.println("** failed to send message");
            e.printStackTrace();
            System.exit(-1);
        }
        op.close();
        System.out.println("message sent");
    }

    /**
     *  rendezvousEvent the rendezvous event
     *
     *@param  event   rendezvousEvent
     */
    public synchronized void rendezvousEvent(RendezvousEvent event) {
        if (event.getType() == event.RDVCONNECT ||
            event.getType() == event.RDVRECONNECT ) {
            notify();
        }
    }

    /**
     *  Starts jxta, and get the pipe, and discovery service
     */
    private void startJxta() {
        try {
            // create, and Start the default jxta NetPeerGroup
            netPeerGroup = PeerGroupFactory.newNetPeerGroup();
            rendezvous = netPeerGroup.getRendezVousService();
            rendezvous.addListener(this);
            // uncomment the following line if you want to start the app defined
            // the NetPeerGroup Advertisement (by default it's the shell)
            // in this case we want use jxta directly.
            // netPeerGroup.startApp(null);

        } catch (PeerGroupException e) {
            // could not instantiate the group, print the stack and exit
            System.out.println("** fatal error : group creation failure");
            e.printStackTrace();
            System.exit(-1);
        }

        // get the pipe service, and discovery
        pipe = netPeerGroup.getPipeService();
        discovery = netPeerGroup.getDiscoveryService();
        System.out.println("--> Reading in pipexample.adv");
        try {
            FileInputStream is = new FileInputStream("pipexample.adv");
            XMLDocument document = (XMLDocument) StructuredDocumentFactory.newStructuredDocument(MimeMediaType.XMLUTF8, is);
            pipeAdv = (PipeAdvertisement) AdvertisementFactory.newAdvertisement(document);
            is.close();
        } catch (Exception e) {
            System.out.println("** failed to read/parse pipe advertisement");
            e.printStackTrace();
            System.exit(-1);
        }
        System.out.println("--> Publishing pipexample.adv");
        discovery.remotePublish(pipeAdv);
        try {
            discovery.publish(pipeAdv);
        } catch (IOException e) {
                e.printStackTrace();
                System.exit(-1);
        }

    }
}

