
/**
 * the serveur implementing getval and inc.
**/

#include "rpc1.h"

static int  value=VALUE;

int *
getval_1_svc(void *argp, struct svc_req *rqstp)
{
	return &value;
}

int *
inc_1_svc(void *argp, struct svc_req *rqstp)
{
	static int  result=0;

    value += 1;

	return &result;
}
