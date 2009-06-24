// Packages a importer
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.StringWriter;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredTextDocument;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.MessageElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.id.IDFactory;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.InputPipe;
import net.jxta.pipe.PipeService;
import net.jxta.pipe.PipeMsgEvent;
import net.jxta.pipe.PipeMsgListener;
import net.jxta.platform.ModuleClassID;
import net.jxta.protocol.ModuleSpecAdvertisement;
import net.jxta.protocol.ModuleClassAdvertisement;
import net.jxta.protocol.PipeAdvertisement;

public class Server {
    
    static PeerGroup group = null;
    private DiscoveryService discoSvc;
    private PipeService pipeSvc;
    private InputPipe myPipe; 				       // Input pipe pour la lecture
    private final static String TAG = "DataTag";               // Message element tag
    private final static String NAMESPACE = "myService";       // Message element namespace
    private final static String FILENAME = "pipeserver.adv";   // Notre Advertisement
    
    public static void main(String args[]) {
	Server myapp = new Server();
	System.out.println("Starting Service Peer ....");
	myapp.startJxta();				       // Initialisation de JXTA
	myapp.startServer();				       // Demarrage du serveur
	myapp.readMessages();				       // Boucle infinie de lecture de message
	System.out.println("Good Bye ....");
	System.exit(0);
    }
    
    private void startJxta() {

	//////////////////////////////////////////////////////////////////////////////////
	//  Initialisation du groupe de travail JXTA. 	(identique au client)		//
	//////////////////////////////////////////////////////////////////////////////////
	try {
	    // On cree un NetPeerGroup
	    group = PeerGroupFactory.newNetPeerGroup();
            
	} catch (PeerGroupException e) {
	    // Si on ne peut pas creer le NetPeergroup
	    System.out.println("Erreur creation de groupe");
	    e.printStackTrace();
	    System.exit(1);
	}
	// Recuperation des donnees du Discovery service et du Pipe service.
	discoSvc = group.getDiscoveryService();
	pipeSvc = group.getPipeService();      
    }
    
    private void startServer() {

      //////////////////////////////////////////////////////////////////////////////////
	//  Le but est ici de creer un PipeAdvertisement, a partir de 			//
	//  l'advertisement de notre fichier .adv					//
	//  Une fois le Pipe Advertisement cree, on va le publier.			//
	//  Ensuite, on creera un input Pipe a partir du Pipe que nous avons initialise.//
	//////////////////////////////////////////////////////////////////////////////////
	
	System.out.println("Demarrage du serveur");
        
	// On essaie de lire dans le fichier FILENAME
	try {
	    System.out.println("Lecture du fichier " + FILENAME);
	    //Initialisation d'un PipeAdvertisement vide.
	    PipeAdvertisement pipeadv = null;				
            
	    try {
		FileInputStream is = new FileInputStream(FILENAME);
		// On copie les donnees du fichier FILENAME dans le Pipe Advertisement
		pipeadv = (PipeAdvertisement)
		    AdvertisementFactory.newAdvertisement(
							  new MimeMediaType("text/xml"), is);
		is.close();
	    } catch (java.io.IOException e) {
		// Si on n'arrive pas a lire dans le fichier FILENAME
		System.out.println("Probleme de lecture...");
		e.printStackTrace();
		System.exit(-1);
	    }
            
	    System.out.println("Advertisement:");
	    
	    // On affiche l'Advertisement.
	    StructuredTextDocument doc = (StructuredTextDocument)
		pipeadv.getDocument(new MimeMediaType("text/plain"));
	    StringWriter out = new StringWriter();
	    doc.sendToWriter(out);
	    System.out.println(out.toString());
	    out.close();       
            
	    // On publie cet Advertisement
	    discoSvc.publish(pipeadv);
	    discoSvc.remotePublish(pipeadv);
            
	    // Creation de l'InputPipe (pour la lecture)
	    myPipe = pipeSvc.createInputPipe(pipeadv);
            
	} catch (Exception ex) {
	    // Si erreur
	    ex.printStackTrace();
	}
    }
    
    private void readMessages() {

	//////////////////////////////////////////////////////////////////////////////////
	//  Nous allons ici creer une boucle infinie qui lit continuellement		//
	//  le contenu de notre Input Pipe.						//
	//////////////////////////////////////////////////////////////////////////////////
	
	Message msg = null;
	MessageElement el = null;
	while (true) { 
            
	    System.out.println("Attente d'un message client");
	    try {                
		// Attente d'un message sur le Pipe (bloquant)
		msg = myPipe.waitForMessage();   
	    } catch (Exception e) {
		// Si erreur
		myPipe.close();
		return;
	    }
            // Si le message est vide			
	    if (msg == null) {
		System.out.println("Message vide recu");
	    }
	    else {
		// Sinon, on recupere le message et on l'affiche.
		el = msg.getMessageElement(NAMESPACE, TAG);
		if (el == null)
		    System.out.println("Message Vide");
		else 
		    System.out.println("Message recu: " + el.toString());
	    }
	    
	}
     }
}
