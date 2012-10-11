

#include <stdio.h>
#include <stdlib.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "ob1.h"
#include "ob1_skel.h"

CORBA_ORB_var orb;
CORBA_BOA_var boa;

/**********************************************************************/
/*** Definition of the exported class                               ***/

class constant_exp : public constant_skel {
public:
    virtual CORBA_Long value() { return VALUE; }
    virtual CORBA_Long precision() { return PRECISION; }
};

/**********************************************************************/
/*** main routine.                                                  ***/

int main(int argc, char** argv)
{
CORBA_Object_var obj;

	orb= CORBA_ORB_init(argc,argv);
	boa= orb->BOA_init(argc,argv);

    CosNaming_NamingContext_var sn;
    obj = orb -> resolve_initial_references("NameService");
    if ( CORBA_is_nil(obj) ) {
        fprintf(stderr,"error: no service name found\n");
        exit(3);
    }
    sn = CosNaming_NamingContext::_narrow(obj);
    if ( CORBA_is_nil(sn) ) {
        fprintf(stderr,"error: no service name found\n");
        exit(3);
    }

	obj= new constant_exp;

    CosNaming_Name name;
    name.length(1);
    name[0].id=   CORBA_string_dup("const");
    name[0].kind= CORBA_string_dup("C1");
    sn -> bind(name,obj);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

