#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "ob3.h"
#include "ob3_skel.h"

CORBA_ORB_var orb;
CORBA_BOA_var boa;

/**********************************************************************/
/*** Definition of the exported class                               ***/

class ietc_exp : public ietc_skel {
public:
    ietc_exp(int v) { vc=v; }
    virtual void maj(CORBA_Long ec) {vc=ec; }
    virtual char* i2c() {
        char tmp[100];
        sprintf(tmp,"%d",vc);
        return CORBA_string_dup(tmp);
    }
    int vc;
};

/**********************************************************************/
/*** main routine.                                                  ***/

int main(int argc, char** argv)
{
	orb= CORBA_ORB_init(argc,argv);
	boa= orb->BOA_init(argc,argv);

    CosNaming_NamingContext_var sn;
    CORBA_Object_var obj;
    obj = orb -> resolve_initial_references("NameService");
    if ( CORBA_is_nil(obj) ) exit(4);
    sn = CosNaming_NamingContext::_narrow(obj);
    if ( CORBA_is_nil(sn) ) exit(4);
    CosNaming_Name name;
    name.length(1);
    name[0].id=   CORBA_string_dup("ietc");

	ietc_var obj2;
    obj2= new ietc_exp(OB2_VC);
    name[0].kind= CORBA_string_dup("1");
	sn->bind(name,obj2);
    obj2= new ietc_exp(OB2_VC2);
    name[0].kind= CORBA_string_dup("2");
	sn->bind(name,obj2);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

