/*
 * PeerGroup
 */


import net.jxta.document.Advertisement;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.protocol.PeerAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;



/**
 *  This is a simple example to illustrate how to start jxta
 */

public class PeerGroupExample implements Runnable {


    static PeerGroup platformGroup = null;
    static PeerGroup netPeerGroup  = null;  // handle to the Net Peer Group
    static PeerGroup fooGrp  = null;  // handle to the Net Peer Group
    static PeerGroupAdvertisement groupAdvertisement = null;

    private DiscoveryService discovery;
    PeerAdvertisement padv;

    private void joinGroup() {
        try {

            netPeerGroup = PeerGroupFactory.newNetPeerGroup();

        }
        catch ( PeerGroupException e) {
            // could not instantiate the group, print the stack and exit
            System.out.println("fatal error : group creation failure");
            e.printStackTrace();
            System.exit(1);
        }
        // obtain our group advertisement
        groupAdvertisement = netPeerGroup.getPeerGroupAdvertisement();
        fooGrp = netPeerGroup.newGroup(fooGrpAdv);
    }

    public 

    static public void main(String args[]) {
        JxtaAppDemo myapp  = new JxtaAppDemo();
        myapp.startJxta();
        myapp.run();
    }
}
