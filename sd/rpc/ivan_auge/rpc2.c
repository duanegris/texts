
/**********************************************************************/
/*** Q1: creation of a simple client & server.                      ***/

#ifdef FOKON_GEN

static void rpc2_q1_create(FILE*stream)
{
int  valeurcachee;

    valeurcachee=random()%1024;

    exercise_subjectPrintf(stream,
        "Réalisation d'une application client server en utilisant les RPCs."
        " Cette application est le jeu de découverte d'un entier caché et les"
        " les services fournit par le serveur sont les suivants:\n"
        "   -- mise à jour de l'entier caché.\n"
        "   -- essai d'une valeur, le service répond 0 si la valeur"
        "      correspond à l'entier caché, 1 si elle est plus grande"
        "      et 2 si elle est plus petite.\n"
        "\n"
        "On demande d'écrire les programmes 'C' suivants:\n"
        "  -a) Générez l'exécutable de nom \"serv\" dans votre répertoire"
        "      principal qui n'écrit rien sur le fichier standard de sortie"
        "      et qui correspond au serveur RPC de l'application."
        "      Au démarage l'entier caché est %d.\n"
        "  -b) Générez l'exécutable de nom \"init\" dans votre répertoire"
        "      principal. Il a un seul argument de type entier dont la"
        "      valeur est utilisée"
        "      pour mettre à jour la valeur caché du serveur \"serv\""
        "      tournant sur cette machine.\n"
        "  -c) Générez l'exécutable de nom \"essai\" dans votre répertoire"
        "      principal. Il a un seul argument entier X dont la valeur est"
        "      testée comme valeur cachée. Le programme renvoie comme"
        "      status:\n"
        "        --- 0 si X est la valeur cachée,\n"
        "        --- 1 si X est plus grand que la valeur cachée,\n"
        "        --- 2 si X est plus petit que la valeur cachée,\n"
        "        --- 3 si erreur.\n"
        "\n"
        "Pour valider, invoquez \"fokon-test\".\n",
        valeurcachee
    );
    fprintf(stream,"%d\n",valeurcachee);
}

#endif 

#ifdef FOKON_TEST

static int rpc2_start_server(cstr name)
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

static int  rpc2_run_init(cstr name, int value)
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

    // compute the note
    if (result.status==0)
        return 20;
    else
        return 0;
}

static int  rpc2_run_essai(cstr name, int value, int expected_status)
{
int      note;
t_result result;
char*    argv[3];
char     tmp[100];

    sprintf(tmp,"%d",value);
    argv[0] = (char*)name;
    argv[1] = tmp;
    argv[2] = 0;

    log_br_level1startPrintf(0, "client \"%s\" %d",name,value);
    run_cmd(&result,2,(char**)argv,0,name);

    // print the status
    if ( result.status==expected_status )
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

    // compute the note
    note=0;
    if (result.status>=0) note += 3;
    if (result.status==expected_status)
        note += 17;

    return note;
}

static int  rpc2_q1_run(FILE*stream,int argc,char**argv)
{
int    vc,vc_org;
int    coef;
int    note;

    if ( fscanf(stream,"%d",&vc_org)!=1 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercice file format).\n");
        return 0;
    }

    /* test 0: just to see if the student is not using a simple
     * file to store the value. The test is weak, there is
     * still many ways to do the exercice without using the
     * RPC. */
    log_br_setMode(0); /* disable printing for this test */
    note= rpc2_run_init("init",0);
    log_br_setMode(1);
    if (note>0) {
        log_br_level1Printf(
            "a server \"serv\" is allready running, please kill it."
        );
        return 1;
    }

    if ( rpc2_start_server("serv") )
        return 1;

    current->cscore.got = 0;
    current->cscore.max = 0;

    /* test 1: */
    coef=1;
    vc=vc_org;
    note= rpc2_run_essai("essai",vc,0);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc2_run_essai("essai",vc+1,1);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc2_run_essai("essai",vc-1,2);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;

    /* test 2: */
    coef=1;
    vc=9999;
    note= rpc2_run_init("init",vc);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc2_run_essai("essai",vc,0);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc2_run_essai("essai",vc+1,1);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    note= rpc2_run_essai("essai",vc-1,2);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;

    /* let the caller to set either success or failure */
    return 1;
}

#endif 

