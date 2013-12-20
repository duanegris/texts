import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

class OperationImpl extends OperationPOA {
  private ORB orb;
  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  // implement sayOperation() method
  public int addition(int a, int b) {
    return(a+b);
  }
    
  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }
}
