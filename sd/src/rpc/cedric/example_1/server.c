#include <stdio.h>
#include <stdlib.h>
#include <rpc/types.h>
#include <rpc/xdr.h>
#include <rpc/rpc.h>


#define PROGNUM 0x20000100
#define VERSNUM 1
#define PROCNUM 1



int* my_function(int *n) { 
  static int res = 1;
  printf("serveur: variable n (debut) 	: %d,\n",*n);
  res = (*n) + 1;
  *n = *n + 1;
  printf("serveur: variable n (fin) 	: %d,\n",*n);
  printf("serveur: variable res 		: %d\n",res);
  return &res;
}


int main (void) {
  registerrpc(/* prognum */ PROGNUM,
	     /* versnum */ VERSNUM,
	     /* procnum */ PROCNUM,
	     /* pointeur sur fonction */  my_function,
	     /* decodage arguments */ (xdrproc_t)xdr_int,
	     /* encodage retour de fonction */ (xdrproc_t)xdr_int);

  svc_run(); /* le serveur est en attente de clients eventuels */
}


