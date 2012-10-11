#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "ob2.h"
#include "ob2_skel.h"

CORBA_ORB_var orb;
CORBA_BOA_var boa;

/**********************************************************************/
/*** Definition of the exported class                               ***/

class jeu_exp : public jeu_skel {
public:
    jeu_exp(int v) { vc=v; }
    virtual void init(CORBA_Long v) { vc=v; }
    virtual CORBA_Long essai(CORBA_Long v) {
        int ret;
        if (v==vc) ret=0; else if (v>vc) ret=1; else ret=2;
        //fprintf(stderr,"v/vc=%d/%d ret=%d\n",v,vc,ret);
        return ret;
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
    name[0].id=   CORBA_string_dup("jeu");

	jeu_var jeu;
    jeu= new jeu_exp(OB2_VC);
    name[0].kind= CORBA_string_dup("1");
	sn->bind(name,jeu);

    jeu= new jeu_exp(OB2_VC2);
    name[0].kind= CORBA_string_dup("2");
	sn->bind(name,jeu);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

