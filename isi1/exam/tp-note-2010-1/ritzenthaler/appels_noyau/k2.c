
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <unistd.h>



int main(int n, char *argv[])
{
	int f1;
	int nb1;
	int nb2;
	int err;
	char *buf;
	

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
	
	err = read(f1, buf, nb1);
	if(err == -1)
	{
		printf("erreur de lecture du fichier\n");
		exit(1);
	}
		
	buf[err] = '\0';
	printf("message lu : %s \n", buf);
	
	
	
	

	return 0;
}