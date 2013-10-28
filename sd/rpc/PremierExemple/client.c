#include <stdio.h>
#include <stdlib.h>
#include <rpc/types.h>
#include <rpc/xdr.h>
#include <rpc/rpc.h>

#define PROGNUM 0x20000100
#define VERSNUM 1
#define PROCNUM 1



int main (int argc, char **argv) {
  int res = 0,  n=99; 
  char *host = argv[1];
  int stat;

  if (argc != 2) { printf("Usage: %s machine_serveur\n",argv[0]); exit(0); }

  printf("[client] before RPC call:  variable n   = %d,\n",n);

  stat = callrpc(/* host */ host,
		 /* prognum */ PROGNUM,
		 /* versnum */ VERSNUM,
		 /* procnum */ PROCNUM,
		 /* encodage argument */ (xdrproc_t) xdr_int,
		 /* argument */ (char *)&n,
		 /* decodage retour */ (xdrproc_t)xdr_int,
		 /* retour de la fonction distante */(char *)&res);

  // just check that n remains unchanged despite call by address
  printf("[client] after  RPC call:  variable n   = %d,\n",n);
  // of course, the result parameter is set
  printf("[client] after  RPC call:  variable res = %d,\n",res);
}



