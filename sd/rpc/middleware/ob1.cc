
/**********************************************************************/
/*** creation of simple corba servers                               ***/
/***                                                                ***/

#ifdef FOKON_TEST

#include <OB/CORBA.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "tmp/ob1.h"
#include "tmp/ob1_skel.h"

#endif 

/**********************************************************************/

#ifdef FOKON_TEST

/* return the note/20 of test */
static int ob1_run_and_get_note(
    int    test_number,   // number of test
    int    value_ref,     // expected value
    int    precision_ref, // expected value
    char*  ns_port,       // 0:no name service; !=0: ns on localhost:port
    char*  obj_name,
    int    _argc,
    char** _argv
);
#endif

#ifdef FOKON_GEN

/* return a constant name & value */
static void ob1_gen_constant(int num, cstr* name, int* value, int* precision)
{
static struct _constant { char n[10]; int v; int p; } table[5];

    if (table[num].p==0 ) {
        int i,dizaine;
        sprintf(table[num].n,"C%d",num);
        table[num].p=(random()%5)+1;
        for (i=0,dizaine=1; i<table[num].p ; i++) dizaine *=10;
        table[num].v=random()%dizaine;
    }
    *name= table[num].n;
    *value= table[num].v;
    *precision= table[num].p;
}

#endif

/**********************************************************************/
/*** Q1: creation of a simple server.                               ***/

#ifdef FOKON_GEN

extern void ob1_q1_create(FILE*stream)
{
cstr n;
int  v,p;

    ob1_gen_constant(1,&n,&v,&p);

    exercise_subjectPrintf(stream,
        "Soit la classe \"constant\" qui definit une constante dont"
        " la description IDL est donnée ci-dessous:\n"
        "    interface constant {\n"
        "       long value();\n" 
        "       long precision();\n"
        "    };\n"
        " avec \"valeur\" qui renvoie la valeur de la constante et"
        " \"precision\" qui renvoie le nombre de chiffres"
        " significatifs.\n"
        "\n"
        "  a) Ecrire un serveur qui renvoie %d avec la precision %d.\n"
        "  b) Lancer le server.\n"
        "  c) Invoquer fokon-test avec l'IOR de l'objet\n",
        v,p
    );
    fprintf(stream,"%d %d\n",v,p);
}

#endif

#ifdef FOKON_TEST

extern int  ob1_q1_run(FILE*stream,int argc,char**argv)
{
int    value_ref, precision_ref;
int    note;

    // check the args
    if ( argc!=2 ) {
        log_br_usagePrintf("%s <ior>\n",argv[0]);
        return 1;
    }

    if ( fscanf(stream,"%d%d",&value_ref,&precision_ref)!=2 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob1_run_and_get_note(1,value_ref,precision_ref,0,0,argc,argv);

    current->cscore.got = note;
    current->cscore.max = 20;

    /* success if note >= 80/100 (16) */
    if ( ((100*current->cscore.got)/current->cscore.max) < 80 )
        return 1;
    else
        return 0;
}

#endif

/**********************************************************************/
/*** Q2: creation of a simple server using the name service.        ***/

#ifdef FOKON_GEN

extern void ob1_q2_create(FILE*stream)
{
cstr n;
int  v,p;

    ob1_gen_constant(1,&n,&v,&p);

    exercise_subjectPrintf(stream,
        "Soit la classe \"constant\" qui definit une constante dont"
        " la description IDL est donnée ci-dessous:\n"
        "    interface constant {\n"
        "       long value();\n" 
        "       long precision();\n"
        "    };\n"
        " avec \"valeur\" qui renvoie la valeur de la constante et"
        " \"precision\" qui renvoie le nombre de chiffres"
        " significatifs.\n"
        "\n"
        "  a) Ecrivez un serveur qui renvoie %d avec la précision %d"
        "     et qui s'enregistre sous le nom (\"const\",\"%s\")"
        "     à la racine de l'arborescence des noms.\n"
        "  b) Lancez le service de noms puis votre serveur.\n"
        "  c) Invoquez fokon-test avec le port du service de noms.\n",
        v,p,n
    );
    fprintf(stream,"%s %d %d\n",n,v,p);
}

#endif

#ifdef FOKON_TEST

extern int  ob1_q2_run(FILE*stream,int argc,char**argv)
{
char   name[10];
int    value_ref, precision_ref;
int    note;
char*  ns_port;

    // check the args
    if ( argc!=2 ) {
        log_br_usagePrintf("%s <port>\n",argv[0]);
        return 1;
    }

    ns_port= argv[1];

    if ( fscanf(stream,"%s%d%d",name,&value_ref,&precision_ref)!=3 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob1_run_and_get_note(
        1,value_ref,precision_ref,ns_port,name,argc,argv);

    current->cscore.got = note;
    current->cscore.max = 20;

    /* success if note >= 80/100 (16) */
    if ( ((100*current->cscore.got)/current->cscore.max) < 80 )
        return 1;
    else
        return 0;
}

#endif

/**********************************************************************/
/*** Q3: creation of a server querying other servers in its         ***/
/***     and using the name service.                                ***/

#ifdef FOKON_GEN

extern void ob1_q3_create(FILE*stream)
{
cstr n1,n2;
int  v1,p1,v2,p2;
char n3[20];

    ob1_gen_constant(1,&n1,&v1,&p1);
    ob1_gen_constant(2,&n2,&v2,&p2);
    sprintf(n3,"%s+%s",n1,n2);

    exercise_subjectPrintf(stream,
        "Soit la classe \"constant\" qui definit une constante dont"
        " la description IDL est donnée ci-dessous:\n"
        "    interface constant {\n"
        "       long value();\n" 
        "       long precision();\n"
        "    };\n"
        " avec \"valeur\" qui renvoie la valeur de la constante et"
        " \"precision\" qui renvoie le nombre de chiffres"
        " significatifs.\n"
        "\n"
        "Ecrivez un serveur dont les caractéristiques sont:\n"
        "    - il renvoie la constante qui est la somme de 2 autres constantes"
        "      du bus logiciel.\n"
        "    - Ces 2 constantes sont référencées dans le service de noms"
        "      à la racine de l'arborescence sous les noms"
        "      (\"const\",\"%s\") et (\"const\",\"%s\").\n"
        "    - il s'enregistre lui meme dans service de noms sous"
        "      le nom (\"const\",\"%s\")"
        "      à la racine de l'arborescence des noms.\n"
        "    - Pour chaque requète, il reflète l'état actuel des 2 constanstes.\n"
        "    - Ses arguments sont les parametres standards de CORBA"
        "      d'identification du service de nom.\n"
        "\n"
        "Pour le tester:\n"
        "  a) Tuez le service de noms puis relancez le.\n"
        "  b) Invoquez fokon-test avec le nom de votre serveur suivi"
        "     du port du service de noms.\n",
        n1,n2,n3
    );
    fprintf(stream,"%s\n",n3);
    fprintf(stream,"%s %d %d\n",n1,v1,p1);
    fprintf(stream,"%s %d %d\n",n2,v2,p2);
}

#endif

#ifdef FOKON_TEST

class ob1_exp : public constant_skel {
public:
    ob1_exp(int _v, int _p, int _i) { v=_v; p=_p; i=_i; }
    virtual CORBA_Long value()      { v+=i; return v; }
    virtual CORBA_Long precision()  { return p; }
private:
    int v,p,i;
};

static int ob1_q3_start_0_I(char* _name, char* _port,
    int _value, int _precision, int _increment)
{
int pid;
    if ( (pid=fork())!=0 ) {
        int status;
        wait(&status);
        if ( WIFSIGNALED(status) ) {
            // child exited  because of a signal which was not
            // caught (something wrong)
            return -WTERMSIG(status);
        } else if ( WIFEXITED(status) ) {
            // child has exited 
            return WEXITSTATUS(status);
        } else {
            // can't appen
            return -1000;
        }
    }

    // child starts here and is a server
    CORBA_Object_var obj;
    int   argc;
    char* argv[4];
    char  buffer[256];
    argc=0;
    argv[argc++]="-ORBservice";
    argv[argc++]="NameService";
    sprintf(buffer,"iiop://localhost:%s/DefaultNamingContext",_port);
    argv[argc++]=buffer;
    argv[argc]=0;
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);
    CORBA_BOA_var boa= orb->BOA_init(argc,argv);

    CosNaming_NamingContext_var sn;
    obj = orb -> resolve_initial_references("NameService");
    if ( CORBA_is_nil(obj) ) exit(1);

    sn = CosNaming_NamingContext::_narrow(obj);
    if ( CORBA_is_nil(sn) )
        exit(1);

    obj= new ob1_exp(_value,_precision,_increment);

    CosNaming_Name name;
    name.length(1);
    name[0].id=   CORBA_string_dup("const");
    name[0].kind= CORBA_string_dup(_name);
    try {
        sn -> bind(name, obj);
    }
    catch (const CosNaming_NamingContext::NotFound&) {
        exit(2);
    }
    catch (const CosNaming_NamingContext::AlreadyBound&) {
        exit(3);
    }
    catch (const CosNaming_NamingContext::InvalidName&) {
        exit(4);
    }

    if ( fork()!=0 ) {
        // server is ok we return 0 to unlock the
        // parent locked in "wait(&stautus)" .
        exit (0);
    } else {
        boa->impl_is_ready(CORBA_ImplementationDef::_nil());
        exit(255);
    }
}

static int ob1_q3_start_0(char* _name, char* _port,
    int _value, int _precision, int _increment)
{
    int ret=ob1_q3_start_0_I(_name,_port,_value,_precision,_increment);
    
    log_br_level1startPrintf(ret==0 ? 1 : 2,
        "serveur of (const,%s) object", _name);
    switch (ret) {
        case 0:
            break;
        case 1:
            log_br_level2itemPrintf(
                "error: no service name found\n");
            break;
        case 2:
            log_br_level2itemPrintf(
                "error: can't record obj under (const,%s)"
                " name (not found)\n",
                _name);
            break;
        case 3:
            log_br_level2itemPrintf(
                "error: can't record obj under (const,%s)"
                " name (name is already present)\n",
                _name);
            break;
        case 4:
            log_br_level2itemPrintf(
                "error: can't record obj under (const,%s)"
                " name (name is invalid)\n",
                _name);
            break;
        default:
            if ( ret<0 )
                log_br_level2itemPrintf(
                    "error: terminated by the %s signal",
                    util_signame(-ret));
            else
                log_br_level2itemPrintf(
                    "error: exited with %d status.\n",
                    ret);
            break;
    }
    return ret;
}

static int ob1_q3_start_1(char* name, char* port)
{
int pid;

    // child starts here and we run the server
    t_path exepath;
    if ( name[0]=='/' )
        strcpy(exepath,name);
    else
        sprintf(exepath,"%s/%s",current->home,name);

    if ( (pid=fork())!=0 ) {
        log_br_level1startPrintf(0,
            "\"%s -ORBservice NameService"
            " iiop://localhost:%s/DefaultNamingContext",
            exepath,port);
        return 0;
    }

    int argc;
    char* argv[10];
    char  buffer[256];
    argc=0;
    argv[argc++]=name;
    argv[argc++]="-ORBservice";
    argv[argc++]="NameService";
    sprintf(buffer,"iiop://localhost:%s/DefaultNamingContext",port);
    argv[argc++]=buffer;
    argv[argc++]=0;
 
    setuid(current->uid);
    setgid(current->gid);
    execvp(exepath,argv);
    exit(255);
}
 
extern int  ob1_q3_run(FILE*stream,int argc,char**argv)
{
char   name[20];
int    value_ref, precision_ref;
char   name1[10];
int    value1_ref,precision1_ref;
char   name2[10];
int    value2_ref,precision2_ref;

int      incr_1,incr_2;
char*    server;
char*    ns_port;
int      pid_1=0,pid_2=0,pid_s=0;
int      test_num;


    // check the args
    if ( argc!=3 ) {
        log_br_usagePrintf("%s <server> <port>\n","usage",argv[0]);
        return 1;
    }

    server= argv[1];
    ns_port= argv[2];
    incr_1=   (random()&3) + 1;
    incr_2= -((random()&3) + 1);

    if ( fscanf(stream,"%s",name)!=1 ||
         fscanf(stream,"%s%d%d",name1,&value1_ref,&precision1_ref)!=3 ||
         fscanf(stream,"%s%d%d",name2,&value2_ref,&precision2_ref)!=3 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    // start const.1 const.2 and server in order
    if ( (pid_1=ob1_q3_start_0(name1,ns_port,value1_ref,precision1_ref,incr_1))!=0 )
        return 1;
    if ( (pid_2=ob1_q3_start_0(name2,ns_port,value2_ref,precision2_ref,incr_2))!=0 )
        return 1;
    usleep(100000);  // to let start the const.1 & const.2 servers 
    if ( (pid_s=ob1_q3_start_1(server,ns_port))!=0 )
        return 1;

    usleep(100000);  // to let time the servers to start
    current->cscore.got = 0;
    current->cscore.max = 0;
    value_ref = value1_ref + value2_ref;
    precision_ref = precision1_ref<precision2_ref ? precision1_ref
                                                  : precision2_ref;
    for (test_num=1 ; test_num<4 ; test_num++ ) {
        int note;
        value_ref += incr_1 + incr_2;
        note= ob1_run_and_get_note(
            test_num,value_ref,precision_ref,ns_port,name,argc,argv);
        current->cscore.got += note;
        current->cscore.max += 20;
    }

    /* let the caller to set either success or failure */
    return 1;
}


/**********************************************************************/
/*** utilities                                                      ***/

static int ob1_ior_client(int argc, char** argv)
{
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

    if (argc!=2) {
        fprintf(stderr,"usage: %s ior\n",argv[0]);
        exit(4);
    }

    CORBA_Object_var obj=orb->string_to_object(argv[1]);
    if ( CORBA_is_nil(obj) ) {
        fprintf(stderr,"error: bad IOR or object not found\n");
        exit(2);
    }
    
    constant_var myobj=constant::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) {
        fprintf(stderr,"error: unexpected object type\n");
        exit(1);
    }

    int value    = myobj->value();
    int precision= myobj->precision();

    printf("%d %d\n",value,precision);

    exit(0);
}

static int ob1_ns_client(int _argc, char** _argv)
{
CORBA_Object* obj;
int   argc;
char* argv[10];
char  buffer[256];

    if (_argc!=3) {
        fprintf(stderr,"usage: %s <name service port> <object name>\n",argv[0]);
        exit(5);
    }
    
    argc=0;
    argv[argc++]=_argv[0];
    argv[argc++]="-ORBservice";
    argv[argc++]="NameService";
    sprintf(buffer,"iiop://localhost:%s/DefaultNamingContext",_argv[1]);
    argv[argc++]=buffer;
    argv[argc]=0;
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

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

    CosNaming_Name name;
    name.length(1);
    name[0].id=   CORBA_string_dup("const");
    //name[0].kind= CORBA_string_dup("1+2");
    name[0].kind= CORBA_string_dup(_argv[2]);
    obj = sn -> resolve(name);
    if ( CORBA_is_nil(obj) ) {
        fprintf(stderr,"error: bad IOR or object not found\n");
        exit(2);
    }
    
    constant_var myobj=constant::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) {
        fprintf(stderr,"error: unexpected object type\n");
        exit(1);
    }

    int value    = myobj->value();
    int precision= myobj->precision();

    printf("%d %d\n",value,precision);

    exit(0);
}

static int ob1_run_and_get_note(
    int    test_number,   // number of test
    int    value_ref,     // expected value
    int    precision_ref, // expected value
    char*  ns_port,       // 0:no name service; !=0: ns on localhost:port
    char*  obj_name,
    int    _argc,
    char** _argv
)
{
t_result result;

    // run the client
    _argv[0]="constant-client";
    if (ns_port) {
        char* argv[4];
        int   argc=0;
        argv[argc++]=_argv[0];
        argv[argc++]=ns_port;
        argv[argc++]=obj_name;
        argv[argc]=0;
        run_cmd(&result,argc,argv,ob1_ns_client,0);
    } else {
        run_cmd(&result,_argc,_argv,ob1_ior_client,0);
    }

    log_br_level1startPrintf(0,
        "client for checking the (const,%s) object",obj_name);

    // print the status
    log_br_level2itemPrintf("%-10s: %3d ","status",result.status);

    // print the meaning of status & the results founds
    // compute the note
    int note=0;
    switch (result.status) {
      case 0: {
        int value,precision;
        sprintf(log_br_currString(),"(sucess)\n");
        if ( sscanf(result.data,"%d%d",&value,&precision)!=2 ) {
            fprintf(stderr,
                "ERROR: fokon-test internal error"
                " (unexpected value number).\n");
            return 0;
        }
        note += 5;
        log_br_level2itemPrintf(
            "%-10s: %4d (expected %3d)\n","value()",value,value_ref);
        log_br_level2itemPrintf(
            "%-10s: %4d (expected %3d)\n","precision()",precision,precision_ref);
        if ( value==value_ref) note += 5;
        if ( precision==precision_ref) note += 5;
        note += 5;
        }
        break;
      case 1:
        sprintf(log_br_currString(),"(unexpected object type)\n");
        note += 5;
        break;
      case 2:
        sprintf(log_br_currString(),"(object not found)\n");
        note += 2;
        break;
      case 3:
        sprintf(log_br_currString(),"(no service name found)\n");
        break;
      case 4:
        sprintf(log_br_currString(),"(no ior provided)\n");
        break;
      case 5:
        sprintf(log_br_currString(),"(no port provided)\n");
        break;
      default:
        if ( !(-33<result.status && result.status<0) ) {
            sprintf(log_br_currString(),"(internal error)\n");
            fprintf(stderr,
                "ERROR: fokon-test internal error"
                " (unexpected exit status (%d).\n",result.status);
            return 0;
        } else {
            sprintf(log_br_currString(),"(%s)\n",
                util_signame(-result.status));
        }
        break;
    }

    return note;
}

#endif
