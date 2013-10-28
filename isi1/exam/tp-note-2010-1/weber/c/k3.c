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

	lseek(fd_open, nb2, SEEK_SET);

	pipe(p1);			//@@SG pas de test code retour
	pipe(p2);

	int pid = fork();

	if ( pid == 0 ) //Fils
	{
		int nb1, i = 0; //i est un compteur pour renvoyer le numero du bloc
		char buf[4096];
		read(p1[0], &nb1, sizeof(int));
		read(p2[0], &buf, nb1);
			i++;
			if ( buf[0] % 2 == 0 )
			{
				char indice = (i + '0');
				write(1, "Bloc : ", strlen("Bloc : ") + 1);
				write(1, &indice, 1);
				write(1, "\n", strlen("\n") + 1);
				write(1, buf, strlen(buf)+1);
				write(1, "\n", strlen("\n") + 1);
			}
			else
			{
				int dev_null = open("/dev/null", O_RDWR);
				write(dev_null, buf, strlen(buf) + 1);
	//gb confusion entre la notion de bloc et la notion de chaîne
	//les utilitaires de traitement de chaine  (strlen, format %d) considerent 
	//qu'une chaine est terminee lorsqu'a ete rencontre un carctere \0. 
	//Or il n'a ete fait aucune supposition sur le contenu des blocs qui peuvent
	//donc contenir zero ou plusieurs \0. Dans le premier cas cela peut conduire
	//a des buffer overflow, dans le second cas on tronque le bloc.
		
			}
		exit(0);
	}

	//Sinon on est dans le pere
	printf("Entrer la valeur de nb1 : ");
	scanf("%d", &nb1);
	printf("Entrer la valeur de nb2 : ");
	scanf("%d", &nb2);
	write(p1[1], &nb1, sizeof(int));
	int res_read = read(fd_open, buf, nb1);
	write(p2[1], buf, res_read);
	close(p1[0]);
	close(p1[1]);
	close(p2[0]);
	close(p2[1]);

	//write(1, buf, res_read);
	//printf("\n");

	return 0;
}
