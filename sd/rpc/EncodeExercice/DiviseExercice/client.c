#include "include.h"

int main (int argc, char **argv) {
  char *host = argv[1];
  entiers2 res;
  entiers2 donnees = {13 , 5};
  
   callrpc(???); 

   printf("client res : %d/%d (q:%d r:%d)\n",donnees.x,donnees.y,res.x,res.y);
 
}
