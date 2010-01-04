#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {

	  int ret;

	  printf("démarrage %d\n", getpid());
	  ret = fork();
	  if (ret == -1 ) {
		    printf("[erreur]: echec fork().\n");
		    exit(1); 
	  }
	  if (ret == 0 ) {
		    printf("[fils] exécution du processus fils (retour fork=%d).\n",ret);
	  }
	  else {
		    printf("[père] exécution du processus père (retour fork=%d).\n",ret);
	  }
	  printf("mon pid est %d\n", getpid());
}


