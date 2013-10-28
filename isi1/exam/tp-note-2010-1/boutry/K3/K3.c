#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>



int main (int argv,char** argc)
{
  int i ;
  char* msg ;
  if (argv != 2)
    printf("vous n avez pas passe le bon nombre d'arguments\n") ;
  else
    {
      int pipe1[2],pipe2[2] ;
      pipe(pipe1) ;	// @@SG pas de test code retour
      pipe(pipe2) ;

      int pid = open (argc[1],O_RDONLY) ;
      printf("Veuillez entrer un entier au clavier\n") ;
      scanf("%i",&i) ;
      while (i > 1024*1024*2*2*2*2)
	{    
	  printf("cette valeur est trop grande veuillez recommencer") ;
	  scanf("%i",&i1) ;
	}

      write(pipe1[1],i,25) ;
      write(pipe2[1],pid,2) ;  //@@gb il faudrait un read dans pid avant
					//puis write du résultat et non de pid

      if (fork() == 0)
	{
	  read (pipe1[0],,25) ;   //@@SG ne compile pas
	  read (pipe2[0],&msg,i+1) ;
	  write(pipe1[1],i,25) ;
	  write (pipe2[1],&msg,sizeof(char*)+1) ;
	}
      read (pipe1[0],&msg,i+1) ;
      if (msg[0]%2 == 0)    //@@SG ne compile pas

      else


  return 0 ;
}
