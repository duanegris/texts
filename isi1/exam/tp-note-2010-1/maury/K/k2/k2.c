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
  int nb2;
  int fich;
  char buf1[10000];
  char buf2[10000];
  if (argc != 2)
    printf("il faut indiquer un fichier, pas deux\n");
  else
    {
      fich = open(argv[1],O_RDONLY);
      printf("tapper un nombre entre 0 et 2e24\n");
      scanf("%d",&nb1);
      printf("tapper un autre nombre entre 0 et 2e24\n");
      scanf("%d",&nb2);
      if(nb1>2e24) printf("valeur trop grande\n");
      read(fich,buf1,nb2+1);
      read(fich,buf2,nb1+1); // @@ SG utiliser lseek()
      printf(buf2);  // @@SG idem question K1
    }
  return 0;
}
