#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>



int main (int argv,char** argc)
{
  int i1,i2 ;
  char* msg ;
  if (argv != 2)
    printf("vous n avez pas passe le bon nombre d'arguments\n") ;
  else
    {
      printf("Veuillez entrer un entier au clavier\n") ;
      scanf("%i",&i1) ;
      while (i1 > 1024*1024*2*2*2*2)
	{    
	  printf("cette valeur est trop grande veuillez recommencer") ;
	  scanf("%i",&i1) ;
	}
      printf("Veuillez entrer un entier au clavier\n") ;
      scanf("%i",&i2) ;

      int pid = open (argc[1],O_RDONLY) ;
      fseek(argc[1],i2,SEEK_SET) ;			//@@ fseek au lieu de lseek -> warning ignoree
      close(0) ;
      dup(pid) ;
      read (0,&msg,i1+1) ;
      write (1,&msg,i1+1) ;
    }

  return 0 ;
}