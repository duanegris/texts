
/**********************************************************************/
/*** Q1: creation of a simple client & server.                      ***/

#ifdef FOKON_GEN

static void rpc1_q1_create(FILE*stream)
{
char name[100];
int  v,incr;

    v=random()%1024;
    incr=random()%5+2;
    sprintf(name,"inc%d",incr);

    exercise_subjectPrintf(stream,
        "On désire réaliser une application client server"
        " en utilisant les RPCs. Le serveur gère une valeur"
        " entière et fournit les services suivants:\n"
        "   -- incrémentation de la valeur gérée de 1.\n"
        "   -- obtention de la valeur gérée.\n"
        "\n"
        "On demande d'écrire les programmes 'C' suivants qui ont"
        " tous aucun argument:\n"
        "  -a) Générez l'exécutable \"serv\" dans votre répertoire"
        "      principal qui n'écrit rien sur le fichier standard"
        "      de sortie"
        "      et qui correspond au serveur RPC de l'application."
        "      Au démarage la valeur gérée est %d.\n"
        "  -b) Générez l'exécutable \"get\" dans votre répertoire"
        "      principal qui écrit sur une ligne la valeur gérée"
        "      (format du printf \"%%d\\n\").\n"
        "  -c) Générez les exécutables \"inc1\" et \"%s\" dans"
        "      votre répertoire principal qui incrémentent"
        "      respectivement de 1 et %d la valeur gérée.\n"
        "\n"
        "Pour valider, invoquez \"fokon-test\".\n",
        v,name,incr
    );
    fprintf(stream,"%s\n",name);
    fprintf(stream,"%d %d\n",v,incr);
}

#endif 

#ifdef FOKON_TEST

static int rpc1_start_server(cstr name)
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

static int  rpc1_q1_run_I(cstr name, int isvalue_ref, int* value_ref)
{
int      note;
t_result result;

    log_br_level1startPrintf(0, "client \"%s\"",name);
    run_cmd(&result,0,0,0,name);

    // print the status
    log_br_level2itemPrintf(
        "%-10s: %3d %s\n",
        "status",result.status,
        result.status==-1002 ? "file is not an excutable" :
        result.status==-1001 ? "file is not a regular file" :
        result.status==-1000 ? "file not found" :
        result.status<  0    ?  util_signame(-result.status) :
        result.status==0  ? "(success)" 
                          : "(expected 0)" 
        
    );

    // compute the note
    note=0;
    if (result.status>=0) note += 3;
    if (result.status==0) note += 3;

    if (result.status!=0)
        return note;

    if (isvalue_ref) {
        int value;
        if (result.sz && result.data &&
            sscanf(result.data,"%d",&value)==1) {
            // a return value is found
            log_br_level2itemPrintf(
                "%-10s: %d (expected %d)\n",
                "value",value,*value_ref
            );
            note += 2;
            if ( value==*value_ref)
                note += 12;
            else
                *value_ref = value;
        } else {
            log_br_level2itemPrintf(
                "%-10s: no value got (expected %d)\n",
                "value",*value_ref
            );
        }
    } else {
        note += 14;
    } 

    return note;
}

static int  rpc1_q1_run(FILE*stream,int argc,char**argv)
{
int    value,incr;
char   name[100];
int    note;
int    i,coef,nb;

    if ( fscanf(stream,"%s%d%d",name,&value,&incr)!=3 ) {
        fprintf(stderr,
            "ERROR: fokon-test internal error"
            " (bad exercice file format).\n");
        return 0;
    }

    /* test 0: just to see if student is not using a simple
     * file to store the value. The test is weak, there is
     * still many ways to do the exercice without using the
     * RPC. */
    log_br_setMode(0); /* disable printing for this test */
    note= rpc1_q1_run_I("get",1,&value);
    log_br_setMode(1);
    if (note>3) {
        log_br_level1Printf(
            "a server \"serv\" is allready running, please kill it."
        );
        return 1;
    }

    if ( rpc1_start_server("serv") )
        return 1;

    current->cscore.got = 0;
    current->cscore.max = 0;

    /* test 1: */
    coef=1;
    note= rpc1_q1_run_I("get",1,&value);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    /* test 2: */
    coef=2;
    for (i=0,nb=(random()%3)+1 ; i<nb ; i+=1 ) {
        rpc1_q1_run_I("inc1",0,0);
        value += 1;
    }
    note= rpc1_q1_run_I("get",1,&value);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;
    /* test 3: */
    coef=2;
    for (i=0,nb=(random()%3)+1 ; i<nb ; i+=1 ) {
        rpc1_q1_run_I(name,0,0);
        value += incr;
    }
    note= rpc1_q1_run_I("get",1,&value);
    current->cscore.got += note*coef;
    current->cscore.max += 20*coef;

    /* let the caller to set either success or failure */
    return 1;
}

#endif 

