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
  char *f , *lu;


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
      //processus fils
      if (pid == 0)
      {
	char *nb1, *nb2;
	char c;
	int nbBloc = 0;

	//fermeture des descripteurs en ecriture
	close(pipe1[1]);
	close(pipe2[1]);

	while (read(pipe2[0], &nb2 , 10) != 0)
	      {
		nbBloc++;
	        read(nb2, c , 1);
		if ( c%2 == 0)
		  printf("%d : %s", nbBloc , nb2);

	      }   
	  }
      }
      //processus pere
      else
      {
	//fermeture des descripteurs en lecture
	close(pipe1[0]);
	close(pipe2[0]);

	f = fopen(fichierE, "r");

	read(f, &lu, 10);
 
	write(pipe2[1], lu , 10);  
      }
    }

  
  return 0 ;
}
