#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

int main() {

	  int ret;

	  printf("[avant fork] processus pid=%d\n",getpid());
	  ret = fork();
	  if (ret == -1 ) {
		   printf("[erreur]: echec fork().\n");
		   exit(1); 
	  }
	  if (ret == 0 ) {
		   char dummy;
		   printf("[fils] exécution du processus fils pid=%d (retour fork=%d).\n",getpid(),ret);
		   printf("\n ......:: Taper 'entree' pour terminer le fils ::.........");
		   scanf("%c",&dummy);
		   exit(0);

	  }
	  else {
		   int status;
		   printf("[père] exécution du processus père (retour fork=%d).\n",ret);
		   printf("[père] attente de fin du fils ...\n"); 
		   ret = wait( &status );
		   printf("[père] fils %d a terminé.\n", ret); 
	  }
}


