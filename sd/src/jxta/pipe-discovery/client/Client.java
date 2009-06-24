import java.io.IOException;
import java.io.StringWriter;
import java.util.Enumeration;
import net.jxta.discovery.DiscoveryEvent;
import net.jxta.discovery.DiscoveryListener;
import net.jxta.discovery.DiscoveryService;
import net.jxta.document.AdvertisementFactory;
import net.jxta.document.MimeMediaType;
import net.jxta.document.StructuredTextDocument;
import net.jxta.endpoint.Message;
import net.jxta.endpoint.StringMessageElement;
import net.jxta.exception.PeerGroupException;
import net.jxta.peergroup.PeerGroup;
import net.jxta.peergroup.PeerGroupFactory;
import net.jxta.pipe.OutputPipe;
import net.jxta.pipe.PipeService;
import net.jxta.protocol.ModuleSpecAdvertisement;
import net.jxta.protocol.PipeAdvertisement;
import net.jxta.protocol.DiscoveryResponseMsg;


public class Client implements  Runnable, DiscoveryListener{
    
    static PeerGroup netPeerGroup = null;
    private DiscoveryService discoSvc;
    private PipeService pipeSvc;
    private OutputPipe myPipe; 				       // Notre Ouput Pipe
    private final static String SERVICE = "PipeSteph";           // Le nom de l'advertisement (que nous allons filtrer)
    private final static String TAG = "DataTag";               // Message element tag
    private final static String NAMESPACE = "myService";       // Message element namespace
    private Enumeration enum1 = null;

    public static void main(String args[]) {
        Client myapp = new Client();
        System.out.println("Starting Client peer ....");
        myapp.startJxta();				       // Initialisation de JXTA
	myapp.run();					       // Demarrage du client
        System.out.println("Good Bye ....");
        System.exit(0);
    }
 
    private void startJxta() {
        
	//////////////////////////////////////////////////////////////////////////////////
	//  Initialisation du groupe de travail JXTA. (identique au serveur) 		//
	//////////////////////////////////////////////////////////////////////////////////

	try {
            // On cree un NetPeerGroup
            netPeerGroup = PeerGroupFactory.newNetPeerGroup();
        } catch (PeerGroupException e) {
            // Si on ne peut pas creer le NetPeergroup
            System.out.println("Erreur creation de groupe");
            e.printStackTrace();
            System.exit(1);
        }
        
        // Recuperation des donnees du Discovery service et du Pipe service.
        discoSvc = netPeerGroup.getDiscoveryService();
        pipeSvc = netPeerGroup.getPipeService();
        
    }
    
   
    public void run() {
        
	//////////////////////////////////////////////////////////////////////////////////
	//  Demarrage du client:							//
	//	- On cree un Listener sur l'objet client qui nous permettra		//
	// 	  d'utiliser une fonction de callback des qu'un evenement aura lieu	//
	//  	  au niveau de la decouverte d'advertisement				//
	//	- On effectue une recherche de l'advertisement voulu (SERVICE)		//
	//////////////////////////////////////////////////////////////////////////////////

        System.out.println("Start the Client");
        System.out.println("searching for the " + SERVICE + " Service advertisement");	// Creation du Listener (fonction de callback : discoveryEvent(DiscoveryEvent ev))
	discoSvc.addDiscoveryListener(this);
	// Recherche distante d'un Advertisement correspondant a notre requete.
        discoSvc.getRemoteAdvertisements(null
						 , DiscoveryService.ADV // on veut un advertisement
						 , "Name"		// on filtre selon le Nom de l'advertisement
						 , SERVICE		// le nom recherche est SERVICE
						 , 1			// nombre d'elements voulus
						 , this);		// on associe la fonction de callback
        // Attente de 2 secondes pour etre sur que le message a bien ete envoye        
        try {
                Thread.sleep(2000);
            } catch (Exception e){}
  	
    }

    public void discoveryEvent(DiscoveryEvent ev) {

	//////////////////////////////////////////////////////////////////////////////////
	//  Fonction de callback							//
	//	On recupere les Advertisements trouves avec la requete de decouverte.	//
	// 	On cree	ensuite un Output Pipe avec l'advertisement recupere.		//
	//  	Enfin on envoit un message a destination du serveur sur ce pipe.	//
	//////////////////////////////////////////////////////////////////////////////////

	System.out.println("Pipe Advertisement trouve");
        DiscoveryResponseMsg res = ev.getResponse();
	enum1 = res.getAdvertisements();
        // On recupere l'advertisement
        PipeAdvertisement pipeadv = (PipeAdvertisement) enum1.nextElement();
        try {
            
            // On le convertit en texte.
            StructuredTextDocument doc = (StructuredTextDocument)
		pipeadv.getDocument(new MimeMediaType("text/plain"));
            
            StringWriter out = new StringWriter();
            doc.sendToWriter(out);
	
	    // On l'affiche
            System.out.println(out.toString());
            out.close();
            
	    // Si l'advertisement recupere est vide, on quitte le programme.
            if (pipeadv == null){
                System.exit(1);
            }
            
	    myPipe = null;
	    System.out.println("Essai de connexion au Pipe...");
	    try {
		// On cree un Output Pipe avec 10 secondes de timeout    
		myPipe = pipeSvc.createOutputPipe(pipeadv,10000);
		} catch (java.io.IOException e) {
		    // Erreur;
	    }
	    if (myPipe == null) {
		System.out.println("Le Pipe n'a pas trouve de destinataire");
		System.exit(1);
	    }                        
	    
	    // Chaine de caractere a envoyer au pipe.
	    String data = "Bonjour a toi, O serveur !";
            
	    // Mise en forme du mesage a destination du pipe.
	    Message msg = new Message();
	    StringMessageElement sme = new StringMessageElement(TAG, data, null);
	    msg.addMessageElement(NAMESPACE, sme);
	    
          // On envoie le message sur le pipe
	    myPipe.send(msg);
	    System.out.println("message \"" + data + "\" envoye au serveur");
          
	} catch (Exception ex) {
	    ex.printStackTrace();
	}        
    }
}
