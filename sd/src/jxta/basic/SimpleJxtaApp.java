/*
 *  Copyright (c) 2001 Sun Microsystems, Inc.  All rights
 *  reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *
 *  1. Redistributions of source code must retain the above copyright
 *  notice, this list of conditions and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in
 *  the documentation and/or other materials provided with the
 *  distribution.
 *
 *  3. The end-user documentation included with the redistribution,
 *  if any, must include the following acknowledgment:
 *  "This product includes software developed by the
 *  Sun Microsystems, Inc. for Project JXTA."
 *  Alternately, this acknowledgment may appear in the software itself,
 *  if and wherever such third-party acknowledgments normally appear.
 *
 *  4. The names "Sun", "Sun Microsystems, Inc.", "JXTA" and "Project JXTA" must
 *  not be used to endorse or promote products derived from this
 *  software without prior written permission. For written
 *  permission, please contact Project JXTA at http://www.jxta.org.
 *
 *  5. Products derived from this software may not be called "JXTA",
 *  nor may "JXTA" appear in their name, without prior written
 *  permission of Sun.
 *
 *  THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *  DISCLAIMED.  IN NO EVENT SHALL SUN MICROSYSTEMS OR
 *  ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 *  SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 *  LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 *  USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *  ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 *  OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 *  SUCH DAMAGE.
 *  ====================================================================
 *
 *  This software consists of voluntary contributions made by many
 *  individuals on behalf of Project JXTA.  For more
 *  information on Project JXTA, please see
 *  <http://www.jxta.org/>.
 *
 *  This license is based on the BSD license adopted by the Apache Foundation.
 *
 *  $Id: SimpleJxtaApp.java,v 1.1 2006-09-13 19:38:41 genaud Exp $
 */
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredDocument;
import net.jxta.document.StructuredDocumentFactory;
import net.jxta.document.XMLDocument;
import net.jxta.exception.PeerGroupException;
import net.jxta.impl.protocol.PlatformConfig;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.PeerGroupAdvertisement;


/**
 *  This the simplist of examples of how an application would start jxta
 */

public class SimpleJxtaApp {

    static PeerGroup netPeerGroup = null;
    static PeerGroupAdvertisement groupAdvertisement = null;
    private DiscoveryService discovery;
    private PipeService pipe;

    public static void main(String args[]) {
        SimpleJxtaApp myapp = new SimpleJxtaApp();
        System.out.println ("Starting jxta ....");
        myapp.startJxta();
        System.out.println ("Good Bye ....");
        myapp.netPeerGroup.stopApp();
    }

    /**
     *  returns a resource InputStream
     *
     *@param  resource         resource name
     *@return                  returns a resource InputStream
     *@exception  IOException  if an I/O error occurs
     */
    private static InputStream getResourceInputStream(String resource) throws IOException {
        ClassLoader cl = SimpleJxtaApp.class.getClassLoader();
        return cl.getResourceAsStream(resource);
    }
    /**
     *  Returns true if the node has been configured, otherwise false
     *
     *@param  home  node jxta home directory
     *@return       true if home/PlatformConfig exists
     */
    private static boolean configured(File home) {
        File platformConfig = new File(home, "PlatformConfig");
        return platformConfig.exists();
    }
    /**
     *  Creates a PlatformConfig with peer name set to name
     *
     *@param  home  node jxta home directory
     *@param  name  node given name (can be hostname)
     */
    private static void createConfig(File home, String name) {
        try {
            InputStream is = getResourceInputStream("PlatformConfig.master");
            home.mkdirs();
            XMLDocument document = (XMLDocument) StructuredDocumentFactory.newStructuredDocument(MimeMediaType.XMLUTF8, is);
            PlatformConfig platformConfig = (PlatformConfig) AdvertisementFactory.newAdvertisement(document);
            is.close();
            platformConfig.setName(name);
            File newConfig = new File(home, "PlatformConfig");
            OutputStream op = new FileOutputStream(newConfig);
            StructuredDocument doc = (StructuredDocument) platformConfig.getDocument(MimeMediaType.XMLUTF8);
            doc.sendToStream(op);
            op.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void startJxta() {
        try {
            File home = new File(System.getProperty("JXTA_HOME",".jxta"));
            System.setProperty("net.jxta.tls.principal", "principal");
            System.setProperty("net.jxta.tls.password", "password");
            if (!configured(home)) {
                createConfig(home, "SimpleJxtaApp");
            }
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
        pipe = netPeerGroup.getPipeService();
    }

}
