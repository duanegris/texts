/*
 * This is sample code generated by rpcgen.
 * These are only templates and you can use them
 * as a guideline for developing your own functions.
 */
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "chat.h"

#define MAX_CLIENTS 2
#define MAX_MSG 3
static int index_msg;  /* the pointer to last mesage in queue */		
static int msg_id=0;   /* the absolute id of a message (whatever the client) */

/* an array that contains ids of connected clients, or 0 if free */
int clients[MAX_CLIENTS];
message mlist[MAX_MSG];


void * chat_null_1_svc(void *argp, struct svc_req *rqstp) {

	static char* result;

	/*
	 * insert server code here
	 */

	return((void*) &result);
}

/**
 * connection operation: returns first available slot, or -1
 * if no available.
 **/
int * connect_1_svc(void *argp, struct svc_req *rqstp) {

	static int i;
	for (i=0;i<MAX_CLIENTS;i++) {
		  if (!clients[i]) {
			    clients[i]=1; /* update to connected */
			    printf("[server] connection request: return id=%d\n",i);
			    return(&i);
		  }
	}
	/* if we arrive here, we did not return, which
	   means there was no free slot */
      i=-1;
	printf("[server] max number of connections reached.\n");
	return(&i);
}

/**
 * disconnect smoothly
 **/
int * disconnect_1_svc(int *cli_id, struct svc_req *rqstp) {
	static int  result;
	/* unmark my position */
	clients[*cli_id]=0;
	printf("[server] client %d: bye, bye.\n",*cli_id);
	return(&result);
}

/**
 * send operation
 * On a send, i.e a message is received by the server, the server 
 * just increments the message counter, and stores this message
 * in the global 'mlist' queue. 
 * @param argp the message received
 * @return the global number of the message
 **/
int *send_1_svc (message *argp, struct svc_req *rqstp) {
	static int  result;
	/* store message in mesage list ... */
	printf("[server] storing message[%d]\n",index_msg);
	mlist[index_msg].message = strdup(argp->message);
	mlist[index_msg].msg_id = argp->msg_id;
	mlist[index_msg].cli_id = argp->cli_id;
	printf("[server] received '%s' from #%d\n",mlist[index_msg].message,argp->cli_id);
	/* ... and assign it a message id */
	index_msg = (index_msg+1); // % MAX_MSG;
	msg_id++;          /* global counter of messages */
	result=msg_id;
	return(&result);
}

/**
 * refresh operation:
 * return the messages stored in queue as a  msg_list
 * It does not return all the list, but only those whose
 * id is greater (hence, more recent) than 'from', which
 * is the highest global id known by the client.
 *
 *			<-- index_msg (where the next element will be)
 * +--------+ ^  
 * |        | |   
 * +--------+ |
 * |        | |   messages to return
 * +--------+ |
 * |        | |  
 * +--------+ v
 * |        |    <---  from
 * +--------+
 * assert( from <= index_msg )
 * @param from the global msg id the highest known by the client
 **/
msg_list *refresh_1_svc(int *from, struct svc_req *rqstp)
{
      static msg_list  returned_msg_list;
	int i;
	int slice_len=index_msg-*from;

	returned_msg_list.msg_list_len = slice_len;  
	returned_msg_list.msg_list_val = malloc( slice_len * sizeof(message));
      for (i= (*from); i<index_msg; i++) {
		returned_msg_list.msg_list_val[i].message = strdup(mlist[i].message);
		returned_msg_list.msg_list_val[i].cli_id = mlist[i].cli_id;
	}	  
	return(&returned_msg_list);
}
