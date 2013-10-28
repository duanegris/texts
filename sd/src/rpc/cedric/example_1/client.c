#include <stdio.h>
#include <stdlib.h>
#include <rpc/types.h>
#include <rpc/xdr.h>
#include <rpc/rpc.h>


#define PROGNUM 0x20000100
#define VERSNUM 1
#define PROCNUM 1



int main (int argc, char **argv) {
  int res = 0,  n=0x41424344; 
  char *host = argv[1];
  int stat;

  if (argc != 2) { printf("Usage: %s machine_serveur\n",argv[0]); exit(0); }

  printf("client: variable n (debut) : %d %s,\n",n,(char *)&n);
  
  stat = callrpc(/* host */ host,
		 /* prognum */ PROGNUM,
		 /* versnum */ VERSNUM,
		 /* procnum */ PROCNUM,
		 /* encodage argument */ (xdrproc_t) xdr_int,
		 /* argument */ (char *)&n,
		 /* decodage retour */ (xdrproc_t)xdr_int,
		 /* retour de la fonction distante */(char *)&res);

    printf("client: variable n (fin) : %d,\n",n);
    printf("client: variable res : %d\n",res);
}

