import java.rmi.*;
import java.math.*;

/* Un code qui utilise l'objet remote Compute doit 
   - obtenir une reference vers cet objet, 
   - creer un objet Task , 
   - appeler l'execution de la tache en passant la tache a Compute
*/

public class Client {
    public static void main(String args[]) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new RMISecurityManager());
        }
        try {
            String name = "rmi://" + args[0] + "/Compute";
            /* obtenir une ref. sur un objet Compute */
            Compute engine = (Compute) Naming.lookup(name); 
            /* instancie un objet de calcul */
            Pi ma_tache = new Pi(Integer.parseInt(args[1]));  
            /* faire calculer la tache envoyee et recuperer */
            String pi = (String) (engine.executeTask(ma_tache));   
            System.out.println(pi);
        } catch (Exception e) {
            System.err.println("Exception du client : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
