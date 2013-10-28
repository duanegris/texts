#include <stdio.h>
#include <stdlib.h>
#include <rpc/rpc.h>

#define PROGNUM 0x20000100
#define VERSNUM 1
#define PROCNUM 1

char **args;

int* my_function(int *n) { 
  static int res; 
  printf("%s:\t variable n (debut) 	: %d,\n",args[0],*n);
  res = (*n) + 1;
  *n = *n + 1;
  printf("%s:\t variable n (fin) 	: %d,\n",args[0],*n);
  printf("%s:\t res		 	: %d,\n",args[0],*n);
  return &res;
}


int main (int argc, char **argv) {
int ret;

  args=argv;
/*	  int registerrpc(unsigned long prognum, unsigned long versnum,
				unsigned long procnum, char *(*procname)(char *),
				xdrproc_t inproc, xdrproc_t outproc);
*/
  printf("%s:\t server started ...\n",argv[0]);
  ret = registerrpc(/* prognum */ PROGNUM,
	     /* versnum */ VERSNUM,
	     /* procnum */ PROCNUM,
	     /* pointeur sur fonction */  my_function,
	     /* decodage arguments */ (xdrproc_t)xdr_int,
	     /* encodage retour de fonction */ (xdrproc_t)xdr_int);

  svc_run(); /* le serveur est en attente de clients eventuels */
}


