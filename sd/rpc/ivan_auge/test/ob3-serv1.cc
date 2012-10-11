#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <OB/CORBA.h>
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

	CORBA_String_var s;
	ietc_var obj;

    obj= new ietc_exp(OB2_VC);
	s= orb->object_to_string(obj);
	printf("%s\n",s._retn());
    fclose(stdout);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

