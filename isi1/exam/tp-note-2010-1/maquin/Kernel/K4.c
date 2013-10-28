#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>
#include <signal.h>

void sigpipe()
{
	printf("Signal SIGPIPE reçu, arrêt.\n");
	exit(-1);
}

int main (int argc, char ** argv)
{
	signal(SIGPIPE, sigpipe);

	if (argc != 2) {
		fprintf(stderr, "usage: %s <fichier>\n", argv[0]);
		exit(-1);
	}

	int fd = open(argv[1], O_RDONLY);
	int blockSize = 10;

	if (blockSize > 16777216) {
		fprintf(stderr, "Impossible de lire un taille de bloc supérieure à 2 puissance 24, arrêt.\n");
		exit(-1);
	}

	int pipe1[2];
	int pipe2[2];

	pipe(pipe1);
	pipe(pipe2);

	int pid = fork();

	if (pid == 0) {
		close(pipe1[1]);
		close(pipe2[1]);

		int pipeClosed = 0;
		
		int size;
		int count = 0;
		int readReturn = 1;
		
		int fdnull = open("/dev/null", O_WRONLY);

		while(readReturn != 0 && readReturn != -1) {

			if (!pipeClosed)
				read(pipe1[0], &size, sizeof(int));

			char * readBlock = calloc((size + 1), sizeof(char));
			readReturn = read(pipe2[0], readBlock, size);

			count++;
			readBlock[size] = '\0';
	
			if (readBlock[size - 1] == '\n') {
				close(pipe1[0]);
				pipeClosed = 1;
			}
	
			if (((int)readBlock[0]) % 2 == 0) {
				printf("Fils : %d %s\n", count, readBlock);
			} else {
				write(fdnull, &readBlock, size + 1);
			}

			free(readBlock);
		}
		
		exit(0);
	}

	close(pipe1[0]);
	close(pipe2[0]);

	char * readBlock = calloc((blockSize + 1), sizeof(char));
	int readReturn = 1;

	while(readReturn != 0 && readReturn != -1) {
		readReturn = read(fd, readBlock, blockSize);
		readBlock[blockSize] = '\0';

		write(pipe1[1], &blockSize, sizeof(int));
		write(pipe2[1], readBlock, blockSize);
	}

	free(readBlock);

	return EXIT_SUCCESS;
}
