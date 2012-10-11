
/**********************************************************************/
/*** Q1: creation of a simple client & server.                      ***/

#ifdef FOKON_GEN

static void rpc3_q1_create(FILE*stream)
{


    exercise_subjectPrintf(stream,
        "Réalisation d'une application client serveur en utilisant les RPCs."
        " Cette application permet de stocker un entier et de le renvoyer"
        " sous forme d'une chaîne de  caractères (type string)"
        " les services fournit par le serveur sont les suivants:\n"
        "   -- mise à jour de l'entier stocké.\n"
        "   -- traduction en chaîne de caractères\n"
        "\n"
        "On demande d'écrire les programmes 'C' suivants:\n"
        "  -a) Générez l'exécutable de nom \"serv\" dans votre répertoire"
        "      principal qui n'écrit rien sur le standart standard de sortie"
        "      et qui correspond au serveur RPC de l'application."
        "      Au démarage l'entier stocké est 0.\n"
        "  -b) Générez l'exécutable de nom \"init\" dans votre répertoire"
        "      principal. Il a un seul argument de type entier dont il"
        "      stocke la valeur dans le serveur \"serv\" tournant sur cette"
        "      machine.\n"
        "  -c) Générez l'exécutable de nom \"i2c\" dans votre répertoire"
        "      principal. Il n'a pas d'argument mais affiche sur le" 
        "      fichier standard de sortie la chaîne de caractères correspondant à"
        "      l'entier stocké.\n"
        "\n"
        "Pour valider, invoquez \"fokon-test\".\n"
    );
    fprintf(stream,"\n");
}

#endif 

#ifdef FOKON_TEST

static int rpc3_start_server(cstr name)
{
t_path exepath;
int    ret;

    ret=run_getExecutable(exepath,name);
    if (ret) {
       log_br_level1startPrintf(2, "\"%s\"", name);
       log_br_level2itemPrintf(
            "error: server not found or not an executable\n");
       return 1;
    }
    log_br_level1startPrintf(0, "\"%s\"", exepath);
    if ( run_isChildDeadStart()!=0 ) {
        ret=run_isChildDeadEnd(0);
        if (ret) {
           log_br_level2itemPrintf("error: server is dead\n");
           return 1;
        } else
           return 0;
    }
    int argc;
    char* argv[10];
    argc=0;
    argv[argc++]=(char*)name;
    argv[argc]=0;
    setuid(current->uid);
    setgid(current->gid);
    execvp(exepath,argv);
    exit(255);
}

static int  rpc3_run_init(cstr name, int value)
{
t_result result;
char*    argv[3];
char     tmp[100];

    sprintf(tmp,"%d",value);
    argv[0] = (char*)name;
    argv[1] = tmp;
    argv[2] = 0;

    log_br_level1startPrintf(0, "client \"%s %d\"",name,value);
    run_cmd(&result,2,(char**)argv,0,name);

    // print the status
    log_br_level2itemPrintf(
        "%-10s: %3d %s\n",
        "status",result.status,
        result.status==-1002 ? "file is not an excutable" :
        result.status==-1001 ? "file is not a regular file" :
        result.status==-1000 ? "file not found" :
        result.status<  0    ?  util_signame(-result.status) :
        result.status== 0    ?  "(success)"
                             :  "(expected 0)"
    );
    //fprintf(stderr,"status %d\n",result.status);
    // compute the note
    if (result.status==0)
        return 20;
    else
        return 0;
}

static int  rpc3_run_i2c(cstr name, int value, int expected_status)
{
int      note;
t_result result;
char*    argv[3];
char     tmp[100];
char     value_str[100];

    sprintf(value_str,"%d",value);
    argv[0] = (char*)name;
    argv[1] = 0;

    log_br_level1startPrintf(0, "client \"%s\" ",name);
    run_cmd(&result,2,(char**)argv,0,name);
    if (result.sz && result.data[result.sz-1]=='\n') {
        result.sz -= 1;
        result.data[result.sz] = 0;
    }

    // print the status
    if ( result.sz && result.data &&
         !strcmp(result.data,tmp) &&
         result.status==expected_status )
        strcpy(tmp,"(success)" );
    else
        sprintf(tmp,"(expected %d)",expected_status);
    log_br_level2itemPrintf(
        "%-10s: %3d %s\n",
        "status",result.status,
        result.status==-1002 ? "file is not an excutable" :
        result.status==-1001 ? "file is not a regular file" :
        result.status==-1000 ? "file not found" :
        result.status<  0    ?  util_signame(-result.status) 
                             : tmp
    );
    log_br_level2itemPrintf(
        "%-10s: %s (expected %d)\n",
        "i2c value",
        result.data!=0?result.data:"",
        value
    );

    // compute the note
    note=0;
    if (result.status>=0) note += 3;
    if (result.status==expected_status)
        note += 2;
    if (strcmp(result.data,value_str)==0)
        note += 15;

    return note;
}

static int  rpc3_q1_run(FILE*stream,int argc,char**argv)
{
int    i,vc,vc_org;
int    coef;
int    note;
vc_org = random()%10000;

#if 0
    if ( fscanf(stream,"%d",&vc_org)!=1 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercice file format).\n");
        return 0;
    }
#endif

    /* test 0: just to see if the student is not using a simple
     * file to store the value. The test is weak, there is
     * still many ways to do the exercice without using the
     * RPC. */
    log_br_setMode(0); /* disable printing for this test */
    note= rpc3_run_init("init",0);
    log_br_setMode(1);
    if (note>0) {
        log_br_level1Printf(
            "a server \"serv\" is allready running, please kill it."
        );
        return 1;
    }

    if ( rpc3_start_server("serv") )
        return 1;

    current->cscore.got = 0;
    current->cscore.max = 0;

    /* test 1: */
    coef=1;
    vc=vc_org;
    note= rpc3_run_init("init",vc);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc3_run_i2c("i2c",vc,0);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;

    /* test 2: 3: ... */
    coef=2;
    for ( i=0 ; i<5 ; i++ ) {
        vc=random()%10000;
        note= rpc3_run_init("init",vc);
        note= rpc3_run_i2c("i2c",vc,0);
        current->cscore.got += note*coef;
        current->cscore.max += 20*coef;
    }

    /* let the caller to set either success or failure */
    return 1;
}

#endif 

