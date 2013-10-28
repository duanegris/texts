
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>



int main(int n, char *argv[])
{
	int f1;
	int f2;
	int nb1;
	int nb2;
	int err;
	char *buf;
	int tube1[2];
	int tube2[2];
	int pid;
	int nb_octet_lu;
	char *path = "/dev/null";
	

	if(n != 2)
	{
		printf("Il faut un parametre.\n");
		exit(1);
	}
	
	f1 = open(argv[1], O_RDONLY);
	if(f1 == -1)
	{
		printf("erreur ouverture du fichier %s\n",argv[1]);
		exit(1);
	}
	
	
	err = pipe(tube1);
	if(err == -1)
	{
		printf("erreur de lors de la création du premier tube\n");
		exit(1);
	}
	err = pipe(tube2);	
	if(err == -1)
	{
		printf("erreur de lors de la création du deuxième tube\n");
		exit(1);
	}
	
	pid = fork();
	
	if(pid == 0)
	{
		close(tube1[1]);
		close(tube2[1]);
		

		nb_octet_lu = read(tube1[0], &nb1, sizeof(int));  // @@SG tres bien de verifier le code de retour
		if(nb_octet_lu == -1)
		{
			printf("fils : erreur de lecture du fichier\n");
			exit(1);
		}
		
		buf = malloc(nb1+1);
		nb_octet_lu = read(tube2[0], buf, nb1);
		if(nb_octet_lu == -1)
		{
			printf("fils : erreur de lecture du fichier\n");
			exit(1);
		}
		
		buf[nb_octet_lu] = '\0';
		
		if(nb_octet_lu > 0)
		{
			if(buf[0] % 2 == 0)
			{
				printf("1 %s\n",buf);
			}
			else
			{
				f2 = open(path, O_WRONLY);
				if(f2 == -1)
				{
					printf("erreur ouverture du fichier %s\n",path);
					exit(1);
				}
				
				err = write(f2,buf,nb_octet_lu);
				if(err == -1)
				{
					printf("erreur écriture fichier %s\n",path);
					exit(1);
				}
			}
		}
		
		
		exit(0);
	}
	
	close(tube1[0]);
	close(tube2[0]);
	
	printf("Entrer un nombre entier nb1 : ");
	
	do{
	scanf("%d",&nb1);
	if(nb1 > 16777216)
		printf("Entrez une valeur plus petite : ");
	} while (nb1 > 16777216);
		
	printf("Entrer un nombre entier nb2 : ");
	scanf("%d",&nb2);
	
	buf = malloc(nb1+1);
	
	err = lseek(f1, nb2, SEEK_SET);
	if(err == -1)
	{
		printf("erreur de la fonction lseek\n");
		exit(1);
	}
	
	nb_octet_lu = read(f1, buf, nb1);
	if(nb_octet_lu == -1)
	{
		printf("erreur de lecture du fichier\n");
		exit(1);
	}
		
	
	err = write(tube1[1],&nb_octet_lu,sizeof(int));
	if(err == -1)
	{
		printf("erreur d'écriture dans tube1\n");
		exit(1);
	}
	err = write(tube2[1],buf,nb1);
	if(err == -1)
	{
		printf("erreur d'écriture dans tube2\n");
		exit(1);
	}
	
	

	return 0;
}