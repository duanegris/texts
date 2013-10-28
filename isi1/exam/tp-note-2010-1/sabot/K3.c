#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdlib.h>
#include <unistd.h>





int main (int argc, char **argv)
{
  int n, nb1, nb2, n2, m;
  char *buf, *buffils1, *buffils2;
  int pipe1[2], pipe2[2];
  if (argc != 2){
    printf("nombre de paramètre incorrect\n");
  }

  else {  

    pipe(pipe1);  // @@ SG tester code retour
    pipe(pipe2);

    if (fork()!=0){    // @@ SG attention au cas -1 
      close(pipe1[0]);
      close(pipe2[0]);
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
	write(pipe1[1], nb1, nb1); //@@gb pas nb1 mais sizeof nb1
        write(pipe2[1], buf, nb1);
      }
      close(pipe1[1]);
      close(pipe2[1]);
    }

    else {
      close(pipe1[1]);
      close(pipe2[1]);

      buffils1=(char *)malloc(nb1*sizeof(char));
      buffils2=(char *)malloc(nb1*sizeof(char));
      read(pipe1[0], buffils1, nb1);      // @@ SG un int aurait suffit, pas la peine de passer par un char *
      m=read(pipe2[0], buffils2, nb1);

      if ((buffils2[0]%2)==0){
	write(1, buffils2, m);
	printf("\n");
      }
      else {
	n2=open("/dev/null", O_WRONLY);
	write(n2, buffils2, nb1);
      }
      close(pipe1[0]);
      close(pipe2[0]);
    }
  }
  
  return 0;
}
