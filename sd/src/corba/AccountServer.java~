// AccountServer.java
// Copyright and License 
import Bank.*;
import Bank.AccountPackage.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

import java.util.Properties;

class  AccountImpl extends AccountPOA {
  private ORB orb;
  private int AccountNumberIndex=0;
  private float AccountBalance[] = new float[100];
  private boolean ActiveAccount[] =  new boolean[100];


  public void setORB(ORB orb_val) {
    orb = orb_val; 
  }
    
  // implement 
  public int createAccount( ) {
	    //search for first inactive account in [0-AccountNumberIndex]
	    for (int i=0; i < AccountNumberIndex; i++) 
			if (!ActiveAccount[i] ) {
				  ActiveAccount[i]=true;
				  return( i );
			}
	    // reach here: means accounts in [0-AccountNumberIndex-1] are all active
	    AccountNumberIndex++;
	    ActiveAccount[AccountNumberIndex-1]=true;
	    return(AccountNumberIndex-1);
  }

  public void  deleteAccount(int accountnum) {
      ActiveAccount[accountnum] = false;
	//if (!this.ActiveAccount[accountnum]) 
	//   throw new Bank.AccountPackage.UnknownAccount("Unused or unknown account");	
  }

  public float makeWithdrawal(int accountnum, float amount) throws  UnknownAccount {
	    if (!ActiveAccount[accountnum])
		throw new UnknownAccount("Unused or unknown account");   
	    if ( AccountBalance[ accountnum ] - amount >=0 ) {
			AccountBalance[ accountnum ] -= amount;
			return( AccountBalance[ accountnum ] );
	    }
	    else 
 		// NotEnoughMoney : do nothing, return previous balance
		return( AccountBalance[ accountnum ] );
	    //else throw NotEnoughMoney(AccountBalance[ accountnum ] - amount);
  } 

  public float makeDeposit(int accountnum, float amount) throws UnknownAccount {
	    System.out.println("[Server] deposit req: "+ amount+ " @ account #"+accountnum);
	    if (!ActiveAccount[accountnum])
			throw new Bank.AccountPackage.UnknownAccount("Unused or unknown account");   
	    AccountBalance[ accountnum ] += amount;
	    System.out.println("[Server] deposit done: new balance="+ AccountBalance[ accountnum ]);
	    return( AccountBalance[ accountnum ] );
  } 


  public float getBalance(int  accountnum) {
	    //if (!ActiveAccount[accountnum])
	    //		throw UnknownAccount("Unused or unknown account");   
	    return( AccountBalance[ accountnum ] );
  } 

  // implement shutdown() method
  public void shutdown() {
    orb.shutdown(false);
  }
}


public class AccountServer {

  public static void main(String args[]) {
    try{
      // create and initialize the ORB
      ORB orb = ORB.init(args, null);


      // get reference to rootpoa & activate the POAManager
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      // create servant and register it with the ORB
      AccountImpl o = new AccountImpl();
      o.setORB(orb); 

      // get object reference from the servant
      org.omg.CORBA.Object ref = rootpoa.servant_to_reference( o );
      Account href = AccountHelper.narrow(ref);
	  
	System.out.println("IOR: "+ orb.object_to_string(ref));

      // get the root naming context
      // NameService invokes the name service
      org.omg.CORBA.Object objRef =
          orb.resolve_initial_references("NameService");
      // Use NamingContextExt which is part of the Interoperable
      // Naming Service (INS) specification.
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      // bind the Object Reference in Naming
      String name = "Account";
      NameComponent path[] = ncRef.to_name( name );
      ncRef.rebind(path, href);

      System.out.println("AccountServer ready and waiting ...");

      // wait for invocations from clients
      orb.run();
    } 
	
      catch (Exception e) {
        System.err.println("ERROR: " + e);
        e.printStackTrace(System.out);
      }
	  
      System.out.println("AccountServer Exiting ...");
	
  }
}
 
