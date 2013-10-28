#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>





int main (int argc, char **argv)
{
  int n, nb1, nb2;
  char *buf;
  if (argc != 2){
    printf("nombre de paramètre incorrect\n");
  }
  else {
    n=open(argv[1], O_RDONLY);
    scanf("%d", &nb1);
    if (nb1 > 16777216){
      printf("la valeur entré est trop grande !\n");
    }
    else {
      scanf("%d", &nb2);
      buf=(char *)malloc(nb1*sizeof(char));
      lseek(n, nb2, SEEK_SET);
      read(n, buf, nb1);
      write(0, buf, nb1);
      printf("\n");
    }
  }

  return 0;
}
