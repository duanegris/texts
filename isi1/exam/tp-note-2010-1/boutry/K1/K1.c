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
    printf("vous n avez pas passe le bon nombre d'arguments\n") ;   // @@SG Pas de message d'usage
  else
    {
      int pid = open (argc[1],O_RDONLY) ;				
      printf("Veuillez entrer un entier au clavier\n") ;
      scanf("%i",&i) ;
      while (i > 1024*1024*2*2*2*2)
	{    
	  printf("cette valeur est trop grande veuillez recommencer") ;
	  scanf("%i",&i) ;
	}
      close(0) ;
      dup(pid) ;            // @@SG pas maitrisé la lecture d'un fichier
      read (0,&msg,i+1) ;   // @@SG pas compris malloc 
      write (1,&msg,i+1) ;
    }

  return 0 ;
}