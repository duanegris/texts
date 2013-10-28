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
		int n, size, depart;
		char* buf, *buf2, *no_use;

		n = open (argv[1],O_RDONLY);
		write(1,"Où voulez-vous demarrer la lecture?   ",38);
		scanf ("%d",&depart);
		write(1,"Quelle quantite de caracteres voulez-vous lire?   ", 51);
		read (0, buf, 10);
		size = atoi (buf);
		lseek (n, depart, SEEK_SET);  // @@SG ok, d'abord bricolage avec un read, puis ok avec lseek

		if (size > 167777216)
		{
			printf("Vous voulez lire trop de caractères, veuillez recommencer avec un fichier plus petit\n");
			exit (0);
		}
		else
		{
			read(n, buf2, size);
			write(1, buf2, size);
			write(1, "\n", 1);
		}
	}

	return (0);
}
