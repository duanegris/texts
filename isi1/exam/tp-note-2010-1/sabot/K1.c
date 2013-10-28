#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>




int main (int argc, char **argv)
{
  int n, nb1;
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
      buf=(char *)malloc(nb1*sizeof(char));
      read(n, buf, nb1);
      write(0, buf, nb1);
      printf("\n");
    }
  }

  return 0;
}
