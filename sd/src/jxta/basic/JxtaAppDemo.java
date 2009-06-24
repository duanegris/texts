/*
 * Copyright (c) 2001 Sun Microsystems, Inc.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution,
 *    if any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *       Sun Microsystems, Inc. for Project JXTA."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must
 *    not be used to endorse or promote products derived from this
 *    software without prior written permission. For written
 *    permission, please contact Project JXTA at http://www.jxta.org.
 *
 * 5. Products derived from this software may not be called "JXTA",
 *    nor may "JXTA" appear in their name, without prior written
 *    permission of Sun.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL SUN MICROSYSTEMS OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of Project JXTA.  For more
 * information on Project JXTA, please see
 * <http://www.jxta.org/>.
 *
 * This license is based on the BSD license adopted by the Apache Foundation.
 *
 * $Id: JxtaAppDemo.java,v 1.1 2006-09-13 19:38:41 genaud Exp $
 */

import java.util.Enumeration;

import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.Advertisement;
import net.jxta.document.MimeMediaType;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.protocol.DiscoveryResponseMsg;
import net.jxta.protocol.PeerAdvertisement;
import net.jxta.protocol.PeerGroupAdvertisement;



/**
 *  This is a quick and simple example to primarly illustrate how to start project jxta
 *  from an application, and how to create a DiscoveryListener interface
 *  This is meant as an on going effort, if you can add to the
 *  example to help others understand, and integrate project jxta,
 *  feel free to do so, please refer to the "todo" section in README .
 */

public class JxtaAppDemo implements Runnable, DiscoveryListener {


    static PeerGroup platformGroup = null;
    static PeerGroup netPeerGroup  = null;
    static PeerGroupAdvertisement groupAdvertisement = null;

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
            System.out.println("fatal error : group creation failure");
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
            // Add ourselves as a DiscoveryListener for DiscoveryResponse events
            discovery.addDiscoveryListener(this);
            while (true) {
                // wait a bit before sending a discovery message
                try {
                    Thread.sleep(60 * 1000);
                } catch(Exception e) {}
                System.out.println("Sending a Discovery Message");
                // look for any peer
                discovery.getRemoteAdvertisements(
                    peer,
                    DiscoveryService.PEER,
                    null,
                    null,
                    5,
                    null);
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
        // let's get the responding peer's advertisement
        padv =  res.getPeerAdvertisement();
        String name = "unknown";
        if (padv != null) {
            // some peers may not respond with their peerAdv
            name = padv.getName();
        }
        System.out.println (" [  Got a Discovery Response ["+
                            res.getResponseCount()+ " elements]  from peer : "+ name +"  ]");

        // now out the goodies
        Advertisement adv=null;
        Enumeration en = res.getAdvertisements();
        if (en != null ) {
            while (en.hasMoreElements()) {
                adv = (Advertisement) en.nextElement();
                System.out.println (adv);
            }
        }

    }

    static public void main(String args[]) {
        JxtaAppDemo myapp  = new JxtaAppDemo();
        myapp.startJxta();
        myapp.run();
    }

}
