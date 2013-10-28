#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>

int main()
{

  int pipe1[2] , pipe2[2];
  pid_t pid;
  int SSIZE_MAX = 200000 ;
  char *fichierE = "test";
  //char *fichierS = "/dev/null";
  char *ent1, *ent2, *f , *lu;


 if ( pipe(pipe1) == -1 )
    {
      printf("erreur pipe1");
      exit(-1);
    }

 if ( pipe(pipe2) == -1 )
    {
      printf("erreur pipe2");
      exit(-1);
    }

  pid = fork();

  if (pid == -1 )
    {
      printf("Erreur fork");
      exit(-1);
    }
  else
    {
      /* ###############
	 processus FILS
	 ############### */
      if (pid == 0)
      {
	char *nb1, *nb2;
	char c;
	int nbBloc = 0;

	//fermeture des descripteurs en ecriture
	close(pipe1[1]);
	close(pipe2[1]);

	while (read(pipe1[0], &nb1 , SSIZE_MAX) != 0)
	  {
	    if (read(pipe2[0], &nb2 , nb1) != 0)  //@@SG  pas acquis : gestion memoire
	      {
		nbBloc++;
	        read(*nb2, &c , 1);			//@@SG pas acquis : gestion memoire

		if ( c%2 == 0)
		  printf("%d : %s", nbBloc , nb2);
	      }   
	  }
	close(pipe1[0]);
	close(pipe2[0]);
      }
      /* ###############
	 processus PERE
	 ############### */
      else
      {
	//fermeture des descripteurs en lecture
	close(pipe1[0]);
	close(pipe2[0]);

	f = fopen(fichierE, "r");

	read(0, &ent1, SSIZE_MAX);
	read(0, &ent2, SSIZE_MAX);

	fseek(f, ent2, SEEK_SET);

	read(f, &lu, ent2);
 
	write(pipe1[1], ent1 , SSIZE_MAX);
	write(pipe2[1], lu , ent2);

	close(pipe1[1]);
	close(pipe2[1]);
      }
      }
    }

  
  return 0 ;
}
