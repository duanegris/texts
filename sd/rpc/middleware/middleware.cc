/**********************************************************************/
/*** middle are exercices                                           ***/
/***                                                                ***/

#include "config.hh"

#define XXX_recordGenRun(_name) \
extern void _name##_recordGen( \
    t_hdltype* type, int*  nbSkey, \
    void (**create)(t_hdltype* hdl, int skey ,FILE* stream), \
    int  (**getSerieId)(t_hdltype* hdl, int skey, int* id, float* weight)); \
extern void _name##_recordRun( \
    t_hdltype* type, \
    void (**init)(t_hdltype* hdl,int skey,FILE* stream,int argc,char**argv), \
    t_runretval (**run)(t_hdltype* hdl,int skey,FILE* stream,int argc,char**argv), \
    void (**output)(t_hdltype* hdl, int skey , t_outstream* out), \
    void (**example)(t_hdltype* hdl, int skey , t_outstream* out))

extern "C" {
#include <top.h>
XXX_recordGenRun(middleware);
XXX_recordGenRun(rpcp);
XXX_recordGenRun(corbap);
XXX_recordGenRun(rpc);
XXX_recordGenRun(corba);
}

/**********************************************************************/
/*** utilities                                                      ***/

/**********************************************************************/
/*** the exercices                                                  ***/
#include "rpc1.c"
#include "rpc2.c"
#include "rpc3.c"
#include "ob1.cc"
#include "ob2.cc"
#include "ob3.cc"

/**********************************************************************/
/*** the exercices table and the handler functions                  ***/
typedef struct _t_middleware {
    int   serie_id;
    float weight;
    void  (*create) (FILE* stream);
    int   (*run)    (FILE* stream, int argc,char**argv);
} t_middleware;


#ifdef FOKON_GEN
#   define TABLE2(x)      { 0, 0,  x##_create, 0 }
#   define TABLES(id,w,x) { id, w, x##_create, 0 }
#endif
#ifdef FOKON_TEST
#   define TABLE2(x)      { 0, 0,  0, x##_run }
#   define TABLES(id,w,x) { id, w, 0, x##_run }
#endif

static t_middleware middleware_table[]={
    TABLE2(       rpc1_q1),
    TABLE2(       rpc2_q1),
    TABLE2(       rpc3_q1),
    TABLE2(       ob2_q1),
    TABLE2(       ob2_q2),
    TABLE2(       ob3_q1),
    TABLE2(       ob3_q2),
    TABLES(8, .1, ob1_q1),
    TABLES(8, .3, ob1_q2),
    TABLES(8, .6, ob1_q3)
};

static t_middleware rpc_p_table[]={
    TABLE2(       rpc1_q1)
};
static t_middleware corba_p_table[]={
    TABLE2(       ob1_q3),
    TABLE2(       ob3_q1),
    TABLE2(       ob3_q2)
};

static t_middleware rpc_table[]={
    TABLE2(       rpc2_q1),
    TABLE2(       rpc3_q1)
};

static t_middleware corba_table[]={
    TABLE2(       ob1_q1),
    TABLE2(       ob1_q2),
    TABLE2(       ob2_q1),
    TABLE2(       ob2_q2),
};

/**********************************************************************/

#ifdef FOKON_GEN
#define XXX_getSerieId(_name,name_of_table) \
static int  _name##_getSerieId(t_hdltype* hdl, int skey, int* id, float* weight) \
  {  \
    if (name_of_table[skey].serie_id<=0) \
        return 0; \
    *id    = name_of_table[skey].serie_id; \
    *weight= name_of_table[skey].weight; \
    return 1; \
  }
XXX_getSerieId(middleware,middleware_table)
XXX_getSerieId(rpcp,rpc_p_table)
XXX_getSerieId(corbap,corba_p_table)
XXX_getSerieId(rpc,rpc_table)
XXX_getSerieId(corba,corba_table)
#endif

/**********************************************************************/

#ifdef FOKON_GEN
#define XXX_create(_name,name_of_table) \
static void _name##_create (t_hdltype* hdl, int subkind, FILE* stream) \
  { name_of_table[subkind].create(stream); }

XXX_create(middleware,middleware_table)
XXX_create(rpcp,rpc_p_table)
XXX_create(corbap,corba_p_table)
XXX_create(rpc,rpc_table)
XXX_create(corba,corba_table)
#endif

/**********************************************************************/
#ifdef FOKON_TEST
static t_runretval middleware_run0(t_middleware* table,
    t_hdltype* hdl, int subkind, FILE* stream, int argc,char**argv)
{
int ret;

    // set the result string to empty
    log_br_init();

    // create a new process group to be able to kill
    // all the created processes
    setpgrp();

    // run the exercice
    ret= table[subkind].run(stream,argc,argv);

    // get the result
    if ( ret!=0 ) {
        // exercice did not run succesfully
        // we say it run succesfully if the got note is > 15/20
        if ( current->cscore.max &&
             (((100*current->cscore.got)/current->cscore.max) >= 75) )
            ret=0;
    } // else { exercice run succesfully
      //        we assume the called has do all
      // }

    // kill all the created processes
    // to not kill ourself we move to our initial group
    setpgid(getpid(),getpgid(getppid()));
    kill(-getpid(),SIGKILL);

    return ret!=0 ? rrv_note : rrv_success;
}

#define XXX_run(_name,name_of_table) \
static t_runretval _name##_run( \
    t_hdltype* hdl, int subkind, FILE* stream, int argc,char**argv) \
{ return middleware_run0(name_of_table,hdl,subkind,stream,argc,argv); }
XXX_run(middleware,middleware_table)
XXX_run(rpcp,rpc_p_table)
XXX_run(corbap,corba_p_table)
XXX_run(rpc,rpc_table)
XXX_run(corba,corba_table)
#endif

/**********************************************************************/
#ifdef FOKON_TEST
#define XXX_output(_name,name_of_table) \
static void _name##_output(t_hdltype* hdl, int subkind, t_outstream* out) \
  { log_br_output(out,0); }
XXX_output(middleware,middleware_table)
XXX_output(rpcp,rpc_p_table)
XXX_output(corbap,corba_p_table)
XXX_output(rpc,rpc_table)
XXX_output(corba,corba_table)
#endif

/**********************************************************************/

#ifdef FOKON_GEN

#define XXX_recordGen(_name,name_of_table) \
    extern void _name##_recordGen( \
        t_hdltype* type, \
        int*  nbSkey, \
        void (**create)(t_hdltype* hdl, int skey ,FILE* stream), \
        int  (**getSerieId)(t_hdltype* hdl, int skey, int* id, float* weight) \
    ){ type->name = #_name; \
       type->hdata= name_of_table; \
       *nbSkey    = sizeof(name_of_table)/sizeof(name_of_table[0]); \
       *create    = _name##_create; \
       *getSerieId= _name##_getSerieId; \
    }

XXX_recordGen(middleware,middleware_table)
XXX_recordGen(rpcp,rpc_p_table)
XXX_recordGen(corbap,corba_p_table)
XXX_recordGen(rpc,rpc_table)
XXX_recordGen(corba,corba_table)

#endif
#ifdef FOKON_TEST

#define XXX_recordRun(_name,name_of_table) \
    extern void _name##_recordRun( \
        t_hdltype* type, \
        void      (**init) (t_hdltype* hdl,int skey,FILE* stream,int argc,char**argv), \
        t_runretval (**run) (t_hdltype* hdl,int skey,FILE* stream,int argc,char**argv), \
        void     (**output) (t_hdltype* hdl, int skey , t_outstream* out), \
        void     (**example) (t_hdltype* hdl, int skey , t_outstream* out) \
    ) { type->name = #_name; \
        type->hdata= name_of_table; \
        *run       = _name##_run; \
        *output    = _name##_output; \
    }

XXX_recordRun(middleware,middleware_table)
XXX_recordRun(rpcp,rpc_p_table)
XXX_recordRun(corbap,corba_p_table)
XXX_recordRun(rpc,rpc_table)
XXX_recordRun(corba,corba_table)

#endif
