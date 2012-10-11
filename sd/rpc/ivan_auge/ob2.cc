
/**********************************************************************/
/*** creation of simple corba servers                               ***/
/***                                                                ***/

#ifdef FOKON_TEST

#include <OB/CORBA.h>
#include <OB/CORBA.h>
#include <OB/CosNaming.h>
#include "tmp/ob2.h"
#include "tmp/ob2_skel.h"

#endif 

/**********************************************************************/

#ifdef FOKON_TEST

#include <ob2.h>

static void ob2_error1(int s) {
    fprintf(stderr,"error: in CORBA_ORB_init\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
static void ob2_error2(int s) {
    fprintf(stderr,"error: failure when getting the name service\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
static void ob2_error3(int s) {
    fprintf(stderr,"error: cannot resolve jeu.1 or jeu.2 name\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
#if 0
static void ob2_error4(int s) {
    fprintf(stderr,"error: bad IOR or object not found\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}
#endif
static void ob2_error5(int s) {
    fprintf(stderr,"error: unexpected object type\n");
    signal(6,SIG_DFL); signal(11,SIG_DFL);
}

static int ob2_init_func(int argc, char**argv)
{
    CORBA_Object_var obj;
    signal(6,ob2_error1); signal(11,ob2_error1); 
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

    char* iorname=argv[1];
    int   arg=atoi(argv[2]);
    int   withsn= argv[3]!=0 ;

    if ( withsn ) {
      signal(6, ob2_error2); signal(11,ob2_error2); 
      CosNaming_NamingContext_var sn;
      obj = orb -> resolve_initial_references("NameService");
      if ( CORBA_is_nil(obj) ) exit(4);
      sn = CosNaming_NamingContext::_narrow(obj);
      if ( CORBA_is_nil(sn) ) exit(4);
      signal(6,ob2_error3); signal(11,ob2_error3); 
      CosNaming_Name name;
      name.length(1);
      name[0].id=   CORBA_string_dup("jeu");
      name[0].kind= CORBA_string_dup(iorname);
      obj=sn->resolve(name);
      if ( CORBA_is_nil(obj) ) exit(4);
    } else {
      signal(6,ob2_error2); signal(11,ob2_error2); 
      obj=orb->string_to_object(iorname);
      if ( CORBA_is_nil(obj) ) exit(4);
    }

    signal(6,ob2_error5); signal(11,ob2_error5); 
    jeu_var myobj=jeu::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) exit(4);

    myobj->init(arg);
    return 0;
}

static int ob2_essai_func(int argc, char**argv)
{
    CORBA_Object_var obj;
    signal(6,ob2_error1); signal(11,ob2_error1); 
    CORBA_ORB_var orb= CORBA_ORB_init(argc,argv);

    char* iorname=argv[1];
    int   arg=atoi(argv[2]);
    int   withsn= argv[3]!=0 ;
    if ( withsn ) {
      signal(6, ob2_error2); signal(11,ob2_error2); 
      CosNaming_NamingContext_var sn;
      obj = orb -> resolve_initial_references("NameService");
      if ( CORBA_is_nil(obj) ) exit(4);
      sn = CosNaming_NamingContext::_narrow(obj);
      if ( CORBA_is_nil(sn) ) exit(4);
      signal(6,ob2_error3); signal(11,ob2_error3); 
      CosNaming_Name name;
      name.length(1);
      name[0].id=   CORBA_string_dup("jeu");
      name[0].kind= CORBA_string_dup(iorname);
      obj=sn->resolve(name);
      if ( CORBA_is_nil(obj) ) exit(4);
    } else {
      signal(6,ob2_error2); signal(11,ob2_error2); 
      obj=orb->string_to_object(iorname);
      if ( CORBA_is_nil(obj) ) exit(4);
    }

    signal(6,ob2_error5); signal(11,ob2_error5); 
    jeu_var myobj=jeu::_narrow(obj);
    if ( CORBA_is_nil(myobj) ) exit(4);

    int ret=myobj->essai(arg);
    return ret;
}

#endif 

/**********************************************************************/
/*** Q1: creation of a simple server.                               ***/

#ifdef FOKON_GEN

extern void ob2_q1_create(FILE*stream)
{
int  v,p;

    v= random()%1024;
    p= random()%1024;

    exercise_subjectPrintf(stream,
        "Réalisation d'une application client server en utilisant CORBA."
        " Cette application est le jeu de découverte d'un entier caché et"
        " est définie par la classe \"jeu\" dont \n"
        " la description IDL est donnée ci-dessous:\n"
        "    interface jeu {\n"
        "       void init(in long ec);\n" 
        "       long essai(in long v);\n"
        "    };\n"
        " L'attribut fonction \"init\" fixe à \"ec\" l'entier caché."
        " L'attribut fonction \"essai\" renvoie 0 si \"v\" est"
        " l'entier caché,"
        " renvoie 1 si \"v\" est plus grand que l'entier caché et"
        " 2 dans les autres cas (\"v\" est plus petit que l'entier caché).\n"
        "\n"
        "  a) Ecrivez un serveur avec 2 jeux, le premier étant"
        "  initialisé à %d et le second à %d.\n"
        "  b) Lancez le server.\n"
        "  c) Invoquer fokon-test avec les IORs des 2 jeux.\n",
        v,p
    );
    fprintf(stream,"%d %d\n",v,p);
}

#endif

#ifdef FOKON_TEST

static int ob2_run_init(
    char* logname,  /* object name for printing log */
    char* port,     /* ns: localhost:port (0 when no ns) */
    char* iorname,  /* ior or name */
    int   arg       /* arg given to the function */
){
char* argv[10];
int   argc=0;
char  value[100],url[100],tmp[100];
t_result result;

    argv[argc++]="init";
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
    run_cmd(&result,argc,(char**)argv,ob2_init_func,0);

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

static int ob2_run_essai(
    char* logname,  /* object name for printing log */
    char* port,     /* ns: localhost:port (0 when no ns) */
    char* iorname,  /* ior or name */
    int   es,       /* expected status */
    int   arg       /* arg given to the function */
){
char* argv[10];
int   argc=0;
char  value[100],url[100],tmp[100];
t_result result;

    argv[argc++]="essai";
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
    run_cmd(&result,argc,(char**)argv,ob2_essai_func,0);

    // print the status
    if ( result.status==es )
        strcpy(tmp,"(success)" );
    else
        sprintf(tmp,"(expected %d)",es);
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
    int note=0;
    if (result.status>=0) note += 3;
    if (result.status==es) note += 17;
    return note;
}

extern int  ob2_q1_run(FILE*stream,int argc,char**argv)
{
int    vc,vc2;
int    vcb,vc2b;
int    note;
char*  iorname= argv[1];
char*  iorname2=argv[2];

    // check the args
    if ( argc!=3 ) {
        log_br_usagePrintf("%s <ior> <ior>\n",argv[0]);
        return 1;
    }

    if ( fscanf(stream,"%d%d",&vc,&vc2)!=2 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob2_run_essai("obj1",0,iorname,0,vc);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj1",0,iorname,1,vc+1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj1",0,iorname,2,vc-1);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob2_run_essai("obj2",0,iorname2,0,vc2);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",0,iorname2,1,vc2+1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",0,iorname2,2,vc2-1);
    current->cscore.got += note;
    current->cscore.max += 20;


    vcb=random()%1024;
    note= ob2_run_init("obj1",0,iorname,vcb);
    current->cscore.got += note;
    current->cscore.max += 20;
    vc2b=random()%1024;
    note= ob2_run_init("obj2",0,iorname2,vc2b);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob2_run_essai("obj1",0,iorname,0,vcb);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",0,iorname2,0,vc2b);
    current->cscore.got += note;
    current->cscore.max += 20;

    /* let the caller to set either success or failure */
    return 1;
}

#endif

/**********************************************************************/
/*** Q2: creation of a simple server using the name service.        ***/

#ifdef FOKON_GEN

extern void ob2_q2_create(FILE*stream)
{
int  v,p;

    v= random()%1024;
    p= random()%1024;

    exercise_subjectPrintf(stream,
        "Réalisation d'une application client server en utilisant CORBA."
        " Cette application est le jeu de découverte d'un entier caché et"
        " est définie par la classe \"jeu\" dont \n"
        " la description IDL est donnée ci-dessous:\n"
        "    interface jeu {\n"
        "       void init(in long ec);\n" 
        "       long essai(in long v);\n"
        "    };\n"
        " L'attribut fonction \"init\" fixe à \"ec\" l'entier caché."
        " L'attribut fonction \"essai\" renvoie 0 si \"v\" est"
        " l'entier caché,"
        " renvoie 1 si \"v\" est plus grand que l'entier caché et"
        " 2 dans les autres cas (\"v\" est plus petit que l'entier caché).\n"
        "\n"
        "  a) Ecrivez un serveur avec 2 jeux, le premier étant"
        "  initialisé à %d et le second à %d."
        "  Le serveur les enregistre respectivement dans le service de nom"
        "  sous les noms \"jeu.1\" et \"jeu.2\" à la racine de"
        "  l'arborescence des noms.\n"
        "  b) Lancez le service de nom puis votre serveur.\n"
        "  c) Invoquez fokon-test avec le port utilisé"
        "  par le service de nom.\n",
        v,p
    );
    fprintf(stream,"%d %d\n",v,p);
}

#endif

#ifdef FOKON_TEST

extern int  ob2_q2_run(FILE*stream,int argc,char**argv)
{
int    vc,vc2;
int    vcb,vc2b;
int    note;
char*  port= argv[1];
char*  iorname= "1";
char*  iorname2= "2";

    // check the args
    if ( argc!=2 ) {
        log_br_usagePrintf("%s <port>\n",argv[0]);
        return 1;
    }

    if ( fscanf(stream,"%d%d",&vc,&vc2)!=2 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercise file format).\n");
        return 0;
    }

    note= ob2_run_essai("obj1",port,iorname,0,vc);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj1",port,iorname,1,vc+1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj1",port,iorname,2,vc-1);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob2_run_essai("obj2",port,iorname2,0,vc2);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",port,iorname2,1,vc2+1);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",port,iorname2,2,vc2-1);
    current->cscore.got += note;
    current->cscore.max += 20;


    vcb=random()%1024;
    note= ob2_run_init("obj1",port,iorname,vcb);
    current->cscore.got += note;
    current->cscore.max += 20;
    vc2b=random()%1024;
    note= ob2_run_init("obj2",port,iorname2,vc2b);
    current->cscore.got += note;
    current->cscore.max += 20;

    note= ob2_run_essai("obj1",port,iorname,0,vcb);
    current->cscore.got += note;
    current->cscore.max += 20;
    note= ob2_run_essai("obj2",port,iorname2,0,vc2b);
    current->cscore.got += note;
    current->cscore.max += 20;

    /* let the caller to set either success or failure */
    return 1;
}

#endif

/**********************************************************************/

