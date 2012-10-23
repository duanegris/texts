import Bank.*;
import Bank.AccountPackage.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;

public class AccountClient
{
  static Account o;


  // ------- just to play with the client ------
  public void lifeCycle( Account a ) {
      float curbal=0;
      // 1- account creation
	int anum = a.createAccount();

	// 2- make a first deposit
      try {
	curbal = a.makeDeposit( anum, (float) 200.0 );
	}
      catch (UnknownAccount e) { System.out.println(e.getMessage()); }
	
	System.out.println("Account #"+anum+ ": balance: "+ curbal);

	// 3- make a withdrawal afterwards 
  	try {
	curbal = a.makeWithdrawal( anum, (float) 100.0 );
	}
      catch (UnknownAccount e) {
		System.out.println(e.getMessage());
	}
      catch (NotEnoughMoney e2) {
		System.out.println(e2.getMessage());
	}

	// 4-print final balance
	System.out.println("Account #"+anum+ ": balance: "+ curbal);
   }

  public static void main(String args[])
    {
      try{
        // create and initialize the ORB
        ORB orb = ORB.init(args, null);

        // get the root naming context
        org.omg.CORBA.Object objRef = 
            orb.resolve_initial_references("NameService");
        // Use NamingContextExt instead of NamingContext. This is 
        // part of the Interoperable naming Service.  
        NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
 
        // resolve the Object Reference in Naming
        String name = "Account";
        o = AccountHelper.narrow(ncRef.resolve_str(name));
        System.out.println("Obtained a handle on server object: " + o);

	  AccountClient client = new AccountClient();
        client.lifeCycle( o );


        } catch (Exception e) {
          System.out.println("ERROR : " + e) ;
          e.printStackTrace(System.out);
          }
    }

}
