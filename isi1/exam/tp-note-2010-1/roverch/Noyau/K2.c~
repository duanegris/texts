#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int main (int argc, char ** argv){
	
	int temp1_1;
	int temp1_2;
	char *temp2;
	int tmp = temp2

	if ( argc != 2 ){
		printf("Il faut entrer 1 argument à ce programme\n");
		return(1);
	}

	printf("entrer le nombre de caractere a lire :\n");
	scanf("%d",&temp1_1);
	printf("entrer la position du curseur dans le fichier :\n");
	scanf("%d",&temp1_2);

        temp2 = (char *) malloc(temp1_1*sizeof(char));
	int f = open(argv[1],O_RDONLY);
	read(f,temp2,temp1_1);
	
	lseek(tmp, temp1_2, SEEK_SET);

	write(1,temp2,temp1_1);
	printf("\n");
	
return(0);
}
