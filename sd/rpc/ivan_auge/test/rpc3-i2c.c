

/**
 * call INCR times the inc function
**/

#include "rpc3.h"

int main (int argc, char *argv[])
{
	CLIENT *clnt;
	char *host="localhost";
    char*  *ret;
    int  i=0;

    if (argc!=1) return 3;

	clnt = clnt_create (host, RPC3, VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}


    ret= i2c_1(&i,clnt);
    if (ret == (char* *) NULL) {
		    clnt_perror (clnt, "call failed");
            exit (3);
    }
    printf("%s\n",*ret);

    exit (0);
}
