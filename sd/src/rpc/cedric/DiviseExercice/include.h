

#include <rpc/types.h>
#include <rpc/xdr.h>
#include <rpc/rpc.h>

#define PROGNUM 0x20000100
#define VERSNUM 1
#define PROCNUM 1

typedef struct { int x; int y; } entiers2;
bool_t xdr_entiers2(XDR *, entiers2 *);


