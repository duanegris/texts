
/**********************************************************************/
/*** creation of simple corba servers                               ***/
/***                                                                ***/

#ifdef FOKON_TEST

#include <OB/CORBA.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "tmp/ob3.h"
#include "tmp/ob3_skel.h"

#endif 

/**********************************************************************/

#ifdef FOKON_TEST

#include <ob3.h>


static void ob3_error1(int s) {
    fprintf(stderr,"error: in CORBA_ORB_init\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
static void ob3_error2(int s) {
    fprintf(stderr,"error: failure when getting the name service\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
static void ob3_error3(int s) {
    fprintf(stderr,"error: cannot resolve ietc.1 or ietc.2 name\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
#if 0
static void ob3_error4(int s) {
    fprintf(stderr,"error: bad IOR or object not found\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
#endif
static void ob3_error5(int s) {
    fprintf(stderr,"error: unexpected object type\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}

static int ob3_maj_func(int argc, char**argv)
{
    CORBA_Object_var obj;
    signal(6,ob3_error1); signal(11,ob3_error1); 
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

    char* iorname=argv[1];
    int   arg=atoi(argv[2]);
    int   withsn= argv[3]!=0 ;

    if ( withsn ) {
      signal(6, ob3_error2); signal(11,ob3_error2); 
      CosNaming_NamingContext_var sn;
      obj = orb -> resolve_initial_references("NameService");
      if ( CORBA_is_nil(obj) ) exit(4);
      sn = CosNaming_NamingContext::_narrow(obj);
      if ( CORBA_is_nil(sn) ) exit(4);
      signal(6,ob3_error3); signal(11,ob3_error3); 
      CosNaming_Name name;
      name.length(1);
      name[0].id=   CORBA_string_dup("ietc");
      name[0].kind= CORBA_string_dup(iorname);
      obj=sn->resolve(name);
      if ( CORBA_is_nil(obj) ) exit(4);
    } else {
      signal(6,ob3_error2); signal(11,ob3_error2); 
      obj=orb->string_to_object(iorname);
      if ( CORBA_is_nil(obj) ) exit(4);
    }

    signal(6,ob3_error5); signal(11,ob3_error5); 
    ietc_var myobj=ietc::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) exit(4);

    myobj->maj(arg);
    return 0;
}

static int ob3_i2c_func(int argc, char**argv)
{
//{ int i; for (i=0;i<=argc;i++) fprintf(stderr,"I2C: argv[%d]=%s\n",i,argv[i]); }
    CORBA_Object_var obj;
    signal(6,ob3_error1); signal(11,ob3_error1); 
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

    char* iorname=argv[1];
    int   withsn= argv[2]!=0 ;
    if ( withsn ) {
      signal(6, ob3_error2); signal(11,ob3_error2); 
      CosNaming_NamingContext_var sn;
      obj = orb -> resolve_initial_references("NameService");
      if ( CORBA_is_nil(obj) ) exit(4);
      sn = CosNaming_NamingContext::_narrow(obj);
      if ( CORBA_is_nil(sn) ) exit(4);
      signal(6,ob3_error3); signal(11,ob3_error3); 
      CosNaming_Name name;
      name.length(1);
      name[0].id=   CORBA_string_dup("ietc");
      name[0].kind= CORBA_string_dup(iorname);
      obj=sn->resolve(name);
      if ( CORBA_is_nil(obj) ) exit(4);
    } else {
      signal(6,ob3_error2); signal(11,ob3_error2); 
      obj=orb->string_to_object(iorname);
      if ( CORBA_is_nil(obj) ) exit(4);
    }

    signal(6,ob3_error5); signal(11,ob3_error5); 
    ietc_var myobj=ietc::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) exit(4);

    char * ret=myobj->i2c();
    printf("%s",ret);
    return 0;
}

#endif 

/**********************************************************************/
/*** Q1: creation of a simple server.                               ***/

#ifdef FOKON_GEN

extern void ob3_q1_create(FILE*stream)
{

    int vg1;
    vg1= (random()%1024) + 10000;

    exercise_subjectPrintf(stream,
        " Réalisation d'une application client server en utilisant CORBA."
        " Cette application permet de stocker dans un objet ietc un "
        " entier et de le renvoyer sous forme d'une chaîne de  caractères"
        " les services fournis par le serveur sont les suivants:\n"
        "   -- mise à jour de l'entier stocké.\n"
	    "   -- traduction en chaîne de caractères de l'entier stocké.\n"
        " Ils correspondent à la description IDL ci-dessous:\n"
        "    interface ietc {\n"
        "       void maj(in long ec);\n" 
        "       string i2c();\n"
        "    };\n"
        " La méthode \"maj\" fixe à \"ec\" l'entier stocké."
        " La méthode  \"i2c\" renvoie la chaîne de caractères correspondant"
        " à l'entier stocké"
        "\n"
        "  a) Ecrivez un serveur dont l'entier a la valeur initial %d \n"
        "  b) Lancez le server.\n"
        "  c) Invoquer fokon-test avec l'IOR de ce serveur .\n",
        vg1
    );
    fprintf(stream,"%d\n",vg1);
}

#endif

#ifdef FOKON_TEST

static int ob3_run_maj  (
    char* logname,  /* object name for printing log */
    char* port,     /* ns: localhost:port (0 when no ns) */
    char* iorname,  /* ior or name */
    int   arg       /* arg given to the function */
){
char* argv[10];
int   argc=0;
char  value[100],url[100],tmp[100];
t_result result;

    argv[argc++]="maj";
    argv[argc++]=iorname;
    sprintf(value,"%d",arg);
    argv[argc++]=value;
    if (port!=0) {
        argv[argc++]="-ORBservice";
        argv[argc++]="NameService";
        sprintf(url,"iiop://localhost:%s/DefaultNamingContext",port);
        argv[argc++]=url;
    }
    argv[argc]=0;

    log_br_level1startPrintf(0, "client \"%s->%s %d\"",logname,argv[0],arg);
    run_cmd(&result,argc,(char**)argv,ob3_maj_func,0);

    // print the status
    if ( result.status==0 )
        strcpy(tmp,"(success)" );
    else
        sprintf(tmp,"(expected %d)",0);
    log_br_level2itemPrintf(
        "%-10s: %3d %s\n",
        "status",result.status,
        result.status==-1002 ? "file is not an excutable" :
        result.status==-1001 ? "file is not a regular file" :
        result.status==-1000 ? "file not found" :
        result.status<  0    ?  util_signame(-result.status) 
                             : tmp
    );

    // compute the note
    if (result.status==0)
        return 20;
    else if (result.status>0)
        return 5;
    else
        return 0;
}

static int ob3_run_i2c(
    char *logname,  /* object name for printing a log*/
    char* port,     /* ns: localhost:port (0 when no ns) */
    char* iorname,  /* ior or name */
    int   ev        /* er expected result   */
){
char* argv[10];
int   argc=0;
char  url[100];
t_result result;
int   gv;

    argv[argc++]="i2c";
    argv[argc++]=iorname;
    if (port!=0) {
        argv[argc++]="-ORBservice";
        argv[argc++]="NameService";
        sprintf(url,"iiop://localhost:%s/DefaultNamingContext",port);
        argv[argc++]=url;
    }
    argv[argc]=0;

    log_br_level1startPrintf(0, "client \"%s->%s\"",logname,argv[0]);
    run_cmd(&result,argc,(char**)argv,ob3_i2c_func,0);

    // print the status
    log_br_level2itemPrintf(
        "%-10s: %3d %s\n",
        "status",result.status,
        result.status==-1002 ? "file is not an excutable" :
        result.status==-1001 ? "file is not a regular file" :
        result.status==-1000 ? "file not found" :
        result.status<  0    ?  util_signame(-result.status) :
        result.status==0     ?  "(success)"
                             :  "(expected 0)"
    );

    // print the stdout
    if ( result.status!=0 ) return 3;
    if ( result.data==0 ) return 4;

    gv=atoi(result.data);
    log_br_level2itemPrintf( "%-10s: %d (expected %d)\n", "stdout",gv,ev);

    if (gv==ev)
        return 20;
    else
        return 6;
}

extern int  ob3_q1_run(FILE*stream,int argc,char**argv)
{
int    vg1;
int    note;
char*  iorname= argv[1];

    // check the args
    if ( argc!=2 ) {
        log_br_usagePrintf("%s <ior>\n",argv[0]);
        return 1;
    }

    if ( fscanf(stream,"%d",&vg1)!=1 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob3_run_i2c("obj",0,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;
    
    int vg2=random()%1024;
    note= ob3_run_maj("obj",0,iorname,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob3_run_i2c("obj",0,iorname,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;

    vg2=random()%1024;
    note= ob3_run_maj("obj",0,iorname,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob3_run_i2c("obj",0,iorname,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;

    /* let the caller to set either success or failure */
    return 1;
}

#endif

/**********************************************************************/
/*** Q2: creation of a simple server using the name service.        ***/

#ifdef FOKON_GEN

extern void ob3_q2_create(FILE*stream)
{

    int vg1,vg2;
    vg1= (random()%1024) + 10000;
    vg2= (random()%1024) + 10000;

    exercise_subjectPrintf(stream,
        " Réalisation d'une application client server en utilisant CORBA."
        " Cette application permet de stocker dans un objet ietc un entier"
        " et de le renvoyer sous forme d'une chaîne de  caractères "
        " les services fournis par le serveur sont les suivants:\n"
        "   -- mise à jour de l'entier stocké.\n"
	    "   -- traduction en chaîne de caractères\n"
        " Ils correspondent à la description IDL ci-dessous:\n"
        "    interface ietc {\n"
        "       void maj(in long ec);\n" 
        "       string i2c();\n"
        "    };\n"
        " La méthode \"maj\" fixe à \"ec\" l'entier stocké."
        " La méthode  \"i2c\" renvoie la chaîne de caractères correspondant"
        " à l'entier stocké.\n"
        "\n"
        "  a) Ecrivez un serveur avec deux ietc, le premier étant"
        "  initialisé à %d et le second à %d."
        "  Le serveur les enregistre respectivement dans le service de nom"
        "  sous les noms \"ietc.1\" et \"ietc.2\" à la racine de"
        "  l'arborescence des noms.\n"
        "  b) Lancez le service de nom puis votre serveur.\n"
        "  c) Invoquez fokon-test avec le port utilisé"
        "  par le service de nom.\n",
        vg1,vg2
    );
    fprintf(stream,"%d %d\n",vg1,vg2);
}

#endif

#ifdef FOKON_TEST

extern int  ob3_q2_run(FILE*stream,int argc,char**argv)
{
int    vg1,vg2;
int    note;
char*  port= argv[1];
char*  iorname= "1";
char*  iorname2= "2";

    // check the args
    if ( argc!=2 ) {
        log_br_usagePrintf("%s <port>\n",argv[0]);
        return 1;
    }

    if ( fscanf(stream,"%d%d",&vg1,&vg2)!=2 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob3_run_i2c("ietc.1",port,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;
    vg1=random()%1024;
    note= ob3_run_maj("ietc.1",port,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob3_run_i2c("ietc.1",port,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;
    vg1=random()%1024;
    note= ob3_run_maj("ietc.1",port,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob3_run_i2c("ietc.1",port,iorname,vg1);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob3_run_i2c("ietc.2",port,iorname2,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;
    vg2=random()%1024;
    note= ob3_run_maj("ietc.2",port,iorname2,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob3_run_i2c("ietc.2",port,iorname2,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;
    vg2=random()%1024;
    note= ob3_run_maj("ietc.2",port,iorname2,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob3_run_i2c("ietc.2",port,iorname2,vg2);
    current->cscore.got += note;
    current->cscore.max += 20;

    /* let the caller to set either success or failure */
    return 1;
}

#endif

/**********************************************************************/

