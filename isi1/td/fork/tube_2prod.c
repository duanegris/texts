/**
 * Demontre une ecriture concurrente dans un pipe.
 * Necessite la connaissance de fork().
 * Le pere cree deux fils A et B qui ecrivent dans le tube, puis se met en lecture permanente
 * du pipe.
 *
 * On observe les séquences de production et consommation.
 *
 **/



#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

/**
 * producteur
 **/
void producteur( char id, int iter, int tube[]) {
int ret;
int i;
	printf("[producteur %c] demarre.\n",id);
	for (i=0;i<iter;i++) {
		   ret = write( tube[1], &id, 1 );
		   //printf("%d ",ret);
	}
	printf("[producteur %c] termine.\n",id);
}



/**
 * main: pere cree 2 fils et devient consommateur
 **/

int main() {
	  int ret;
	  int tube[2];

        /* creat pipe */ 
        ret = pipe( tube );
	  if (ret == -1 ) {
		   printf("[erreur]: echec pipe().\n");
		   exit(1); 
	  }

	  /* creat fils */ 
	  ret = fork();
	  if (ret == -1 ) {
		    printf("[erreur]: echec fork().\n");
		    exit(1); 
	  }

	  if (ret == 0 ) {  /* fils 1 = producteur 1*/
		    producteur( 'A', 1000, tube );
	  }
	  else {
		    /* creat fils */ 
		    ret = fork();
		    if (ret == -1 ) {
				printf("[erreur]: echec fork().\n");
				exit(1); 
		    }

		    /* pere ecrit , fils lit */
		    if (ret == 0 ) {  /* fils 2 = producteur 2*/
				producteur( 'B', 100 , tube );
		    }
		    else {
				/* le pere = consommateur */
				printf("[consommateur] demarre.\n");
				while (1) {  /* boucle infinie */
					  char lu;
					  ret = read( tube[0], &lu, 1 );
					  printf("%c",lu);
					  fflush(stdout);
				}

		    }
	  }

}
