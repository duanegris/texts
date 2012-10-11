/**
 * call INCR times the inc function
**/

#include "rpc1.h"

int main (int argc, char *argv[])
{
	CLIENT *clnt;
	char *host="localhost";
    int  *ret;
    int  i;

	clnt = clnt_create (host, RPC1, VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}

    for ( i=0 ; i<INCR ; i++ ) {
        ret= inc_1(0,clnt);
	    if (ret == (int *) NULL) {
		    clnt_perror (clnt, "call failed");
            exit (2);
        }
	}
    exit (0);
}
