/*
 * Please do not edit this file.
 * It was generated using rpcgen.
 */

#ifndef _CHAT_H_RPCGEN
#define _CHAT_H_RPCGEN

#define RPCGEN_VERSION	199506

#include <rpc/rpc.h>


struct message {
	int cli_id;
	long timestamp;
	char *message;
	int msg_id;
};
typedef struct message message;
#ifdef __cplusplus
extern "C" bool_t xdr_message(XDR *, message*);
#elif __STDC__
extern  bool_t xdr_message(XDR *, message*);
#else /* Old Style C */
bool_t xdr_message();
#endif /* Old Style C */


#ifdef __cplusplus
extern "C" bool_t xdr_message(XDR *, message*);
#elif __STDC__
extern  bool_t xdr_message(XDR *, message*);
#else /* Old Style C */
bool_t xdr_message();
#endif /* Old Style C */


typedef struct {
	u_int msg_list_len;
	message *msg_list_val;
} msg_list;
#ifdef __cplusplus
extern "C" bool_t xdr_msg_list(XDR *, msg_list*);
#elif __STDC__
extern  bool_t xdr_msg_list(XDR *, msg_list*);
#else /* Old Style C */
bool_t xdr_msg_list();
#endif /* Old Style C */


#define CHAT ((rpc_uint)0x20000001)
#define VERSION_ONE ((rpc_uint)1)

#ifdef __cplusplus
#define CHAT_NULL ((rpc_uint)0)
extern "C" void * chat_null_1(void *, CLIENT *);
extern "C" void * chat_null_1_svc(void *, struct svc_req *);
#define CONNECT ((rpc_uint)1)
extern "C" int * connect_1(void *, CLIENT *);
extern "C" int * connect_1_svc(void *, struct svc_req *);
#define DISCONNECT ((rpc_uint)2)
extern "C" int * disconnect_1(int *, CLIENT *);
extern "C" int * disconnect_1_svc(int *, struct svc_req *);
#define SEND ((rpc_uint)3)
extern "C" int * send_1(message *, CLIENT *);
extern "C" int * send_1_svc(message *, struct svc_req *);
#define REFRESH ((rpc_uint)4)
extern "C" msg_list * refresh_1(int *, CLIENT *);
extern "C" msg_list * refresh_1_svc(int *, struct svc_req *);

#elif __STDC__
#define CHAT_NULL ((rpc_uint)0)
extern  void * chat_null_1(void *, CLIENT *);
extern  void * chat_null_1_svc(void *, struct svc_req *);
#define CONNECT ((rpc_uint)1)
extern  int * connect_1(void *, CLIENT *);
extern  int * connect_1_svc(void *, struct svc_req *);
#define DISCONNECT ((rpc_uint)2)
extern  int * disconnect_1(int *, CLIENT *);
extern  int * disconnect_1_svc(int *, struct svc_req *);
#define SEND ((rpc_uint)3)
extern  int * send_1(message *, CLIENT *);
extern  int * send_1_svc(message *, struct svc_req *);
#define REFRESH ((rpc_uint)4)
extern  msg_list * refresh_1(int *, CLIENT *);
extern  msg_list * refresh_1_svc(int *, struct svc_req *);

#else /* Old Style C */
#define CHAT_NULL ((rpc_uint)0)
extern  void * chat_null_1();
extern  void * chat_null_1_svc();
#define CONNECT ((rpc_uint)1)
extern  int * connect_1();
extern  int * connect_1_svc();
#define DISCONNECT ((rpc_uint)2)
extern  int * disconnect_1();
extern  int * disconnect_1_svc();
#define SEND ((rpc_uint)3)
extern  int * send_1();
extern  int * send_1_svc();
#define REFRESH ((rpc_uint)4)
extern  msg_list * refresh_1();
extern  msg_list * refresh_1_svc();
#endif /* Old Style C */

#endif /* !_CHAT_H_RPCGEN */
