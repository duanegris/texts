#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
		     

int main (int argc, char* argv[])
{

	if (argc != 2)
	{
		printf("Le nombre d'arguments est incorrect. Veuillez recommencer\n");
		exit(-1);
	}
	else
	{
		int n, size;
		char* buf, *buf2;

		n = open (argv[1],O_RDONLY);
		write(1,"Quelle quantite de caracteres voulez-vous lire?   ", 51);  //@@SG pas de printf() ?
		read (0, buf, 10);    // @@SG Pas de scanf() ?
		size = atoi (buf);

		if (size > 167777216)
		{
			printf("Vous voulez lire trop de caractères, veuillez recommencer avec un fichier plus petit\n");
			exit (0);
		}
		else
		{
			read(n, buf2, size);   // @@SG buf2 non alloué :  gestion memoire mal comprise
			write(1, buf2, size);
			write(1, "\n", 1);
		}
	}

	return (0);
}
