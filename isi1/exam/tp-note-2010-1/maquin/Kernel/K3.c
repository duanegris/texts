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

	int pipe1[2];
	int pipe2[2];

	pipe(pipe1);   // @@SG pas de test succes pipe()
	pipe(pipe2);

	int pid = fork();

	if (pid == 0) {
		close(pipe1[1]);
		close(pipe2[1]);
		
		int size;
		int count = 0;
		
		read(pipe1[0], &size, sizeof(int));

		int fdnull = open("/dev/null", O_WRONLY);

		char * readBlock = calloc((size + 1), sizeof(char));
		read(pipe2[0], readBlock, size);
		count++;
		readBlock[size] = '\0';

		if (((int)readBlock[0]) % 2 == 0) {
			printf("Fils : %d %s\n", count, readBlock);
		} else {
			write(fdnull, &readBlock, size + 1);
		}

		free(readBlock);
		
		exit(0);
	}

	close(pipe1[0]);
	close(pipe2[0]);

	char * readBlock = calloc((blockSize + 1), sizeof(char));

	read(fd, readBlock, blockSize);
	readBlock[blockSize] = '\0';

	write(pipe1[1], &blockSize, sizeof(int));  // @@ SG bien compris
	write(pipe2[1], readBlock, blockSize);

	free(readBlock);

	return EXIT_SUCCESS;
}
