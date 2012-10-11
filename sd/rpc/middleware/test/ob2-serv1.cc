
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <OB/CORBA.h>
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

	CORBA_String_var s;
	jeu_var obj;

    obj= new jeu_exp(OB2_VC);
	s= orb->object_to_string(obj);
	printf("%s ",s._retn());

    obj= new jeu_exp(OB2_VC2);
    s= orb->object_to_string(obj);
	printf("%s\n",s._retn());
    fclose(stdout);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

