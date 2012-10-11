
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <OB/CORBA.h>
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
	orb= CORBA_ORB_init(argc,argv);
	boa= orb->BOA_init(argc,argv);

	constant_var obj= new constant_exp;

	CORBA_String_var s= orb->object_to_string(obj);
	printf("%s\n",s._retn());
    fclose(stdout);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

