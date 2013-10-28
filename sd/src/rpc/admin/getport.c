/** Un programme qui affiche le numero de port RPC 
 *  sur un host donne (argv[1])
 *  d'un service dont le numero est donne (argv[2])
 * 
 * Rappel sur la structure rpcent (decrite dans rpcent.h)
 *   struct rpcent { 
 *         char *r_name ; // nom du programme RPC 
 *         char ** aliases; 
 *         int r_number; //numero du programme 
 *   } 
 **/
#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/socket.h>

int main(int argc, char **argv)
{
	char   *hostname = argv[1];
	int    num_serv = atoi(argv[2]);
	int    iport;
	struct rpcent  *p;
	p = getrpcbynumber(num_serv);
	//retourne le rpcent du service 
	printf("Nom du service %s \n", p->rname);
	iport = getrpcport(hostname,num_serv, 1, iPPPRO_UDP);
	printf("Port affecte au service %d\n",iport);
}
