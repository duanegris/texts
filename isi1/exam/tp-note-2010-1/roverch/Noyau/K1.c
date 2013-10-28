#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>


int main (int argc, char ** argv){
	
	int temp1;
	char *temp2;
	
	if ( argc != 2 ){
		printf("Il faut entrer 1 argument à ce programme\n");
		return(1);
	}

	printf("entrer le nombre de caractere a lire :\n");
	scanf("%d",&temp1);
        temp2 = (char *) malloc(temp1*sizeof(char));
	int f = open(argv[1],O_RDONLY);
	read(f,temp2,temp1);
	
	write(1,temp2,temp1);
	printf("\n");

return(0);
}
