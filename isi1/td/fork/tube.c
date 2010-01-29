/**
 * Demontre une communication entre deux processus a l'aide de pipe().
 * Necessite la connaissance de fork().
 * Le pere ecrit dans le pipe, le fils lit.
 *
 * pipe( int tube[2] ) cree une paire de file descriptors, tube[0] et tube[1],
 * tube[0] est connecté à l'extrémité en lecture, tube[1] à l'extrémité en écriture.
 *
 *               open FD table
 *              ------------------
 *              | 0   |  stdin   |
 *              |     |
 *    tube[0]   | n   |  outpipe | <-+
 *    tube[1]   | n+1 |  inpipe  | --+
 *
 **/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>

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

	  /* pere ecrit , fils lit */
	  if (ret == 0 ) {
		   char valeur;
		   printf("[fils] tube[0][1]=[%d %d]\n", tube[0],tube[1] );
		   printf("[fils] bloqué en lecture sur tube[0]=%d\n", tube[0] );
		   close( tube[1] );
		   ret = read( tube[0], &valeur, 1 );
		   printf("[fils] a lu %c\n", valeur );
		   
	  }
	  else {
		   char valeur;
		   printf("[pere] tube[0][1]=[%d %d]\n", tube[0],tube[1] );
		   close( tube[0] );
		   printf("[pere] saisir valeur ?\n");
		   scanf("%c",&valeur);
		   ret = write( tube[1], &valeur, 1 );
		   printf("[pere] a écrit %c dans tube[1]=%d\n",valeur,tube[1]);

	  }
}


