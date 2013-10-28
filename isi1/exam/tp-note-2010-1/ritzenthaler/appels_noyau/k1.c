
#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int main(int n, char *argv[])
{
	int f1;
	int nb1;
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
	
	printf("Entrer un nombre entier : ");
	
	do{
	scanf("%d",&nb1);
	if(nb1 > 16777216)
		printf("Entrez une valeur plus petite : ");
	} while (nb1 > 16777216);
		
	
	buf = malloc(nb1+1);
	
	err = read(f1, buf, nb1);
	buf[nb1] = '\0';
	printf("message lu : %s \n", buf);
	//@@gb pb s'il y a d�j� un \0	
	
	
	

	return 0;
}