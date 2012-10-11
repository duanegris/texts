/**
 * Get the value and print it to the standart output.
**/

#include "rpc1.h"

int main (int argc, char *argv[])
{
	CLIENT *clnt;
	char *host="localhost";
    int  *value;

	clnt = clnt_create (host, RPC1, VERS, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror (host);
		exit (1);
	}

	value = getval_1(0,clnt);
	if (value == (int *) NULL) {
		clnt_perror (clnt, "call failed");
        exit (2);
	} else {
        printf("%d\n",*value);
        exit (0);
    }
}
