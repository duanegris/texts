#include <stdio.h>
#include <stdlib.h>
#include <limits.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>
		     

int main (int argc, char* argv[])
{
int *pipe1, *pipe2, pid;

pipe(pipe1);	// @@SG pas de verification code retour 
pipe(pipe2);

if ((pid = fork()) == 0)  // @@SG attention au cas -1
{
	int size, n;
	char* buf, *buf2;

	close (pipe1[1]);
	close (pipe2[1]);

	size = atoi (buf);

	read (pipe1[0], buf, 10);		// @@ SG buf non alloue
	read (pipe2[0], buf2 ,atoi (buf));	// @@ SG buf2 alloue que dans le pere

	if (! (*buf2 %2))
		write(1,buf2, atoi (buf));
	else
	{
		n = open ("/dev/null",O_WRONLY);
		write(n,buf2, atoi (buf));
	}
}
else
{
	close (pipe1[0]);
	close (pipe2[0]);

	if (argc != 2)  // @@SG verifier ça plus tôt, inutile de lancer le fils avant ....
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
		lseek (n, depart, SEEK_SET);
		size = atoi (buf);
		buf2=(char *)malloc(size*sizeof(char));

		if (size > 167777216)
		{
			printf("Vous voulez lire trop de caractères, veuillez recommencer avec un fichier plus petit\n");
			exit (0);
		}
		else
		{
			read(n, buf2, size);
			printf("[%s]\n",buf2);
			write (pipe1[1], buf, 10);
			write (pipe2[1], buf2, size);
		}
	}
}

	return (0);
}
