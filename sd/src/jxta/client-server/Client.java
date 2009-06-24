import java.io.IOException;
import java.io.StringWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;

import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredTextDocument;
import net.jxta.document.TextElement;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.id.IDFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeID;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.ModuleSpecAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;
import net.jxta.protocol.PipeAdvertisement;


/**
 *  Client Side: This is the client side of the JXTA-EX1
 *  application. The client application is a simple example on how to
 *  start a client, connect to a JXTA enabled service, and invoke the
 *  service via a pipe advertised by the service. The
 *  client searches for the module specification advertisement
 *  associated with the service, extracts the pipe information to
 *  connect to the service, creates a new output to connect to the
 *  service and sends a message to the service.
 *  The client just sends a string to the service no response
 *  is expected from the service.
 */

public class Client {

    static PeerGroup netPeerGroup = null;
    static PeerGroupAdvertisement groupAdvertisement = null;
    private DiscoveryService discovery;
    private PipeService pipes;
    private OutputPipe myPipe; // Output pipe to connect the service
    private Message msg;

    public static void main(String args[]) {
        Client myapp = new Client();
        System.out.println ("Starting Client peer ....");
        myapp.startJxta();
        System.out.println ("Good Bye ....");
        System.exit(0);
    }

    private void startJxta() {
        try {
            // create, and Start the default jxta NetPeerGroup
            netPeerGroup = PeerGroupFactory.newNetPeerGroup();
        } catch (PeerGroupException e) {
            // could not instantiate the group, print the stack and exit
            System.out.println("fatal error : group creation failure");
            e.printStackTrace();
            System.exit(1);
        }

        // this is how to obtain the group advertisement
        groupAdvertisement = netPeerGroup.getPeerGroupAdvertisement();
        // get the discovery, and pipe service
        System.out.println("Getting DiscoveryService");
        discovery = netPeerGroup.getDiscoveryService();
        System.out.println("Getting PipeService");
        pipes = netPeerGroup.getPipeService();
        startClient();
    }

    // start the client
    private void startClient() {

        // Let's initialize the client
        System.out.println("Start the Client");

        // Let's try to locate the service advertisement
        // we will loop until we find it!
        System.out.println("searching for the JXTA-EX1 Service advertisement");
        Enumeration en = null;
        while (true) {
            try {

                // let's look first in our local cache to see
                // if we have it! We try to discover an adverisement
                // which as the (Name, JXTA-EX1) tag value
                //
                en = discovery.getLocalAdvertisements(DiscoveryService.ADV
                                                    , "Name"
                                                    , "JXTASPEC:JXTA-EX1");

                // Ok we got something in our local cache does not
                // need to go further!
                if ((en != null) && en.hasMoreElements()) {
                    break;
                }

                // We could not find anything in our local cache, so let's send a
                // remote discovery request searching for the service
                // advertisement.
                discovery.getRemoteAdvertisements(null
                                              , DiscoveryService.ADV
                                              , "Name"
                                              , "JXTASPEC:JXTA-EX1",1, null);

                // The discovery is asynchronous as we do not know
                // how long is going to take
                try { // sleep as much as we want. Yes we
                    // should implement asynchronous listener pipe...
                    Thread.sleep(2000);
                } catch (Exception e) {}

            }
            catch (IOException e) {
                // found nothing!  move on
            }

            System.out.print(".");
        }

        System.out.println("we found the service advertisement");

        // Ok get the service advertisement as a Spec Advertisement
        ModuleSpecAdvertisement mdsadv = (ModuleSpecAdvertisement) en.nextElement();
        try {

            // let's print the advertisement as a plain text document
            StructuredTextDocument doc = (StructuredTextDocument)
                                         mdsadv.getDocument( MimeMediaType.TEXT_DEFAULTENCODING );

            StringWriter out = new StringWriter();
            doc.sendToWriter(out);
            System.out.println(out.toString());
            out.close();

            // we can find the pipe to connect to the service
            // in the advertisement.
            PipeAdvertisement pipeadv = mdsadv.getPipeAdvertisement();

            // Ok we have our pipe advertiseemnt to talk to the service
            // create the output pipe endpoint to connect
            // to the server, try 3 times to bind the pipe endpoint to
            // the listening endpoint pipe of the service
            for (int i=0; i<3; i++) {
                myPipe = pipes.createOutputPipe(pipeadv, 10000);
            }

            // create the data string to send to the server
            String data = "Hello my friend!";

            // create the pipe message
            msg = new Message();
            StringMessageElement sme = new StringMessageElement("DataTag", data , null);
            msg.addMessageElement(null, sme);

            // send the message to the service pipe
            myPipe.send (msg);
            System.out.println("message \"" + data + "\" sent to the Server");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Client: Error sending message to the service");
        }
    }
