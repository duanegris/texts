#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <ctype.h>


int main (int argc,char * argv[])
{
  int nb1;
  int fich;
  int pid1;
  int buf[1024];
  int pipe1[2];
  int pipe2[2];

  fich = open(argv[1],O_RDONLY);
  printf("tapper un nombre entre 0 et 2e24\n");
  scanf("%d",&nb1);
  if(nb1>2e24) printf("valeur trop grande\n");

  pipe(pipe1);
  pipe(pipe2);
  if((pid1 = fork()) == 0)
    {
      close(pipe1[1]);
      close(pipe2[1]);
      read(&pipe1[0],buf,5);   // @@ SG pourquoi 5 ? = sizeof(int)+1 ??
    }
  else 
    {
      close(pipe1[0]);
      close(pipe2[0]);
      write(pipe1[1],&nb1 ,sizeof(nb1));
    }
  
  if((pid1 = fork()) == 0)		// @@SG arghh ! un deuxieme fils créé !!
    {
      read(&pipe2[0],buf,nb1+1);
    }
  else 
    {
      write(pipe2[1],fich,nb1+1);
    }
  

  return 0;
}