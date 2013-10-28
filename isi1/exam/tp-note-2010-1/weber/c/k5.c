#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <math.h>
#include <unistd.h>
#include <string.h>
#include <signal.h>

/*
 * Variables globales car utilisees dans le sighandler
 */
int p1[2], p2[2]; //descripteurs pour les pipes
char buf[4096];
int fd_open;

void ecrire()
{
	alarm(1); //Minuterie qui tue le processus au bout d'une seconde
	int tmp;

	while ( (tmp = read(fd_open, buf, 4096)) != 0 )
		write(p2[1], buf, tmp);

	printf("(pere) je meurs\n");
	exit(0);
}

void handler(int n)
{
	printf("(pere) passage en mode vrac\n");
	ecrire();
}
//@@gb La question demandait que le pere traite SIGPIPE au lieu de mourir, mais aussi
//qu'il reprenne son travail normal pendant 60 s. Glissons sur le delai transformé en 
//1 s, mais il reprend en mode degrade dans ecrire et surtout sera tue par le SIGALRM
// au bout d'une seconde sans emettre le message demande

int main(int argc, char** argv)
{
	if ( argc != 2 )
	{
		fprintf(stderr, "Usage : %s <fichier>\n", argv[0]);
		exit(1);
	}

	if ( (fd_open = open(argv[1], O_RDONLY)) == -1 )
	{
		//On teste si on peut ouvrir le fichier
		fprintf(stderr, "%s ne peut pas etre ouvert !\n", argv[1]);
		exit(1);
	}

	//Si on est la, c'est que le fichier existe et a ete ouvert
	/*
	 * //INUTILE DANS LA QUESTION 5
	int nb1, nb2;

	if ( nb1 > pow(2, 24) )
	{
		fprintf(stderr, "nb1 ne peut pas depasser 2^24\n");
		exit(1);
	}
	*/

	//lseek(fd_open, nb2, SEEK_SET);

	pipe(p1);
	pipe(p2);

	int pid = fork();

	if ( pid == 0 ) //Fils
	{
		close(p1[1]);
		close(p2[1]);
		int nb1 = 10, i = 0; //i est un compteur pour renvoyer le numero du bloc
		char buffer[4096];
		int vrac = 0; //=0 si on lit par bloc de 10, 1 sinon
		int tmp;
		
		while ( (tmp = read(p2[0], buffer, nb1)) != 0 )
		{
			if ( vrac == 1 )
				write(1, buffer, tmp);
			else
			{
				read(p1[0], &nb1, sizeof(int)); //On lit le nombre d'octets a recuperer
				if ( buffer[nb1-1] == '\n' ) //Si le dernier octet de ce bloc est un RC
				{
					close(p1[0]); //On ferme le pipe de taille en lecture
					vrac = 1;
					nb1 = 4096;
					write(1, buffer, tmp);
				}
				else
				{
					if ( buffer[0] % 2 == 0 )
					{
						printf("Bloc %d :\n", i);
						write(1, buffer, strlen(buffer)+1);
						write(1, "\n", strlen("\n") + 1);
					}
					else
					{
						int dev_null = open("/dev/null", O_RDWR);
						write(dev_null, buffer, strlen(buffer) + 1);
					}
				}
				i++;
			}
		}
		exit(0);
	}

	//Sinon on est dans le pere
	signal(SIGPIPE, handler);
	close(p1[0]);
	close(p2[0]);
	/*
		//Inutile puisqu'on lit tout le fichier
	printf("Entrer la valeur de nb1 : ");
	scanf("%d", &nb1);
	printf("Entrer la valeur de nb2 : ");
	scanf("%d", &nb2);
	*/
	
	int res_read;
	while ( (res_read = read(fd_open, buf, 10)) != 0 )
	{
		write(p1[1], &res_read, sizeof(int));
		write(p2[1], buf, res_read);
	}
	write(p2[1], buf, res_read);
	close(p1[1]);
	close(p2[1]);

	return 0;
}
