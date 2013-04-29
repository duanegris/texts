/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */

#include "chat.h"


void
chat_1( char* host )
{
	CLIENT *clnt;
	void  *result_1;
	char*  chat_null_1_arg;
	int  *result_2;
	char*  connect_1_arg;
	int  *result_3;
	int  disconnect_1_arg;
	int  *result_4;
	message  send_1_arg;
	msg_list  *result_5;
	int  refresh_1_arg;
	clnt = clnt_create(host, CHAT, VERSION_ONE, "udp");
	if (clnt == NULL) {
		clnt_pcreateerror(host);
		exit(1);
	}
	result_1 = chat_null_1((void*)&chat_null_1_arg, clnt);
	if (result_1 == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	result_2 = connect_1((void*)&connect_1_arg, clnt);
	if (result_2 == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	result_3 = disconnect_1(&disconnect_1_arg, clnt);
	if (result_3 == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	result_4 = send_1(&send_1_arg, clnt);
	if (result_4 == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	result_5 = refresh_1(&refresh_1_arg, clnt);
	if (result_5 == NULL) {
		clnt_perror(clnt, "call failed:");
	}
	clnt_destroy( clnt );
}


main( int argc, char* argv[] )
{
	char *host;

	if(argc < 2) {
		printf("usage: %s server_host\n", argv[0]);
		exit(1);
	}
	host = argv[1];
	chat_1( host );
}