#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <ctype.h>
#include <string.h>


int main (int argc,char * argv[])
{
  int nb1;
  int fich;
  char buf[10000];
  if (argc!=2)       // @@SG arguments de main() : a revoir car il y a eu du tatonnement 
    printf("il faut indiquer un fichier, pas deux\n");
  else
    {
      fich = open(argv[1],O_RDONLY);
      printf("tapper un nombre entre 0 et 2e24\n");
      scanf("%d",&nb1);
      if(nb1>2e24) printf("valeur trop grande\n");
	*buf='\0';
      int n=read(fich,buf,nb1);  // @@SG pourquoi +1 ?
      write(1,buf,n);    // @@SG printf : a revoir
      printf("\n");
    }
  return 0;
}
