#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main (int argc, char ** argv)
{
	if (argc != 2) {
		fprintf(stderr, "usage: %s <fichier>\n", argv[0]);
		exit(-1);
	}

	int fd = open(argv[1], O_RDONLY);
	int blockSize;
	int offset;

	printf("Nombre d'octets à lire ? ");
	scanf("%d", &blockSize);

	printf("\nOffset pour la tête de lecture ? ");
	scanf("%d", &offset);

	if (blockSize > 16777216) {
		fprintf(stderr, "Impossible de lire un taille de bloc supérieure à 2 puissance 24, arrêt.\n");
		exit(-1);
	}

 	lseek(fd, offset, SEEK_SET);

	char * readBlock = calloc((blockSize + 1), sizeof(char));

	read(fd, readBlock, blockSize);
	readBlock[blockSize] = '\0';
	printf("%s\n", readBlock);

	free(readBlock);

	return EXIT_SUCCESS;
}
