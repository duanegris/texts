/**
 * call INCR times the inc function
**/

#include "rpc2.h"

int main (int argc, char *argv[])
{
	CLIENT *clnt;
	char *host="localhost";
    int  *ret;
    int  i;

    if (argc!=2) return 3;

	clnt = clnt_create (host, RPC2, VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}


    i= atoi(argv[1]);
    ret= init_1(&i,clnt);
    if (ret == (int *) NULL) {
		    clnt_perror (clnt, "call failed");
            exit (3);
    }

    exit (0);
}
