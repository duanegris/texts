#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>
#include <unistd.h>

int main(int argc, char** argv)
{
	if ( argc != 2 )
	{
		fprintf(stderr, "Usage : %s <fichier>\n", argv[0]);
		exit(1);
	}

	int fd_open;
	if ( (fd_open = open(argv[1], O_RDONLY)) == -1 )
	{
		//On teste si on peut ouvrir le fichier
		fprintf(stderr, "%s ne peut pas etre ouvert !\n", argv[1]);
		exit(1);
	}

	//Si on est la, c'est que le fichier existe et a ete ouvert
	int nb1;
	char buf[4096]; //gb il faudrait 4096*4096 ou 1<<24
	printf("Entrer la valeur de nb1 : ");
	scanf("%d", &nb1);

	if ( nb1 > pow(2, 24) )
	{
		fprintf(stderr, "nb1 ne peut pas depasser 2^24\n");
		exit(1);
	}

	int res_read = read(fd_open, buf, nb1); // gb : risque de buffer overflow
	write(1, buf, res_read);
	printf("\n");

	return 0;
}
