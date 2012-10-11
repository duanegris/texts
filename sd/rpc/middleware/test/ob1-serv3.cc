
#include <stdio.h>
#include <stdlib.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "ob1.h"
#include "ob1_skel.h"

CORBA_ORB_var orb;
CORBA_BOA_var boa;
CosNaming_NamingContext_var sn;

/**********************************************************************/
/*** Definition of the exported class                               ***/

class constant_exp : public constant_skel {
public:
    constant_exp() {
        CosNaming_Name name;
        CORBA_Object_var obj;

        name.length(1);
        name[0].id=   CORBA_string_dup("const");

        name[0].kind= CORBA_string_dup("C1");
        obj = sn->resolve(name);
        c1= constant::_narrow(obj);
//fprintf(stderr,"C1->value()=%d\n",(int)c1->value());
        
        name[0].kind= CORBA_string_dup("C2");
        obj = sn->resolve(name);
        c2= constant::_narrow(obj);
//fprintf(stderr,"C2->value()=%d\n",(int)c2->value());
    }
    virtual CORBA_Long value()
        { return c1->value() + c2->value(); }
    virtual CORBA_Long precision()
        { int p1,p2; p1=c1->precision(); p2=c2->precision();
          return p1>p2 ? p2 : p1; }

    constant_var c1;
    constant_var c2;
};

/**********************************************************************/
/*** main routine.                                                  ***/

int main(int argc, char** argv)
{
CORBA_Object_var obj;

	orb= CORBA_ORB_init(argc,argv);
	boa= orb->BOA_init(argc,argv);

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
    name[0].kind= CORBA_string_dup("C1+C2");
    sn -> bind(name,obj);

	boa->impl_is_ready(CORBA_ImplementationDef::_nil());

	return 0;
}

