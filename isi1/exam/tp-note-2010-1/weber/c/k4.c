#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>
#include <unistd.h>
#include <string.h>

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
	int nb1, nb2;
	int p1[2], p2[2]; //descripteurs pour les pipes
	char buf[4096];

	if ( nb1 > pow(2, 24) )
	{
		fprintf(stderr, "nb1 ne peut pas depasser 2^24\n");
		exit(1);
	}

	//lseek(fd_open, nb2, SEEK_SET);

	pipe(p1);
	pipe(p2);

	int pid = fork();

	if ( pid == 0 ) //Fils
	{
		close(p1[1]);
		close(p2[1]);
		int nb1 = 10, i = 0; //i est un compteur pour renvoyer le numero du bloc
		char buf[4096];
		int vrac = 0; //=0 si on lit par bloc de 10, 1 sinon
		int tmp;
		
		while ( (tmp = read(p2[0], &buf, nb1)) != 0 ) //@@gb : pas besoin du &,mais inoffensif
		{
			if ( vrac == 1 )
			{
				nb1 = 4096;
				//while ( read(p2[0], &buf, 4096) != 0 )
				write(1, &buf, 4096);
					//On lit par bloc de 4096
			}
			else
			{
				read(p1[0], &nb1, sizeof(int)); //On lit le nombre d'octets a recuperer

				if ( buf[nb1] == '\n' ) //Si le dernier octet de ce bloc est un RC
				{
					close(p1[0]); //On ferme le pipe de taille en lecture
					vrac = 1;
					printf("PASSAGE EN VRAC\n");
				}
				else
				{
					if ( buf[0] % 2 == 0 )
					{
						printf("Bloc %d :\n", i);	//@@SG oublie incrementer i
						write(1, "\n", strlen("\n") + 1);
						write(1, buf, strlen(buf)+1);
						write(1, "\n", strlen("\n") + 1);
					}
					else
					{
						int dev_null = open("/dev/null", O_RDWR);
						write(dev_null, buf, strlen(buf) + 1);
					}
				}
			}
		}
		exit(0);
	}

	//Sinon on est dans le pere
	/*
	close(p1[0]);
	printf("Entrer la valeur de nb1 : ");
	scanf("%d", &nb1);
	printf("Entrer la valeur de nb2 : ");
	scanf("%d", &nb2);
	*/
	//@@gb : pb : le close(p1[0]) est indispensable, sinon le pere ne recevra pas
	// le SIGPIPE. Il est bien present ci-dessus mais en commentaire et n'est
	// pas repris ci-dessous. ON pourrait donc dire que c'est une faute d'inattention
	
	//int res_write;
	//res_write = write(p1[1], &nb1, sizeof(int));

	int res_read;
	int b=0;
	while ( (res_read = read(fd_open, buf, 10)) != 0 )
	{
		write(p1[1], &res_read, sizeof(int));
		write(p2[1], buf, res_read);
	}
	write(p2[1], buf, res_read);
	close(p1[1]);
	close(p2[0]);
	close(p2[1]);

	//write(1, buf, res_read);
	//printf("\n");

	return 0;
}
