/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "rpc3.h"

int valeur=VALUE;

extern char** i2c_1_svc(void *x, struct svc_req *rqstp)
{
	static char  table[100];
	static char* result=table;
    sprintf(table,"%d",valeur);
	return &result;
}

extern int* init_1_svc(int *argp, struct svc_req *rqstp)
{
	static int  result=0;

    valeur=*argp;
	return &result;
}
