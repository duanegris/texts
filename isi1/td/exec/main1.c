/* main1.c */
#include <stdio.h>
#include <stdlib.h>
#include <fcntl.h>
#include <dlfcn.h>
#include "compress.h"
#define MAX_SIZE 4096

UBYTE buffer_in[MAX_SIZE + 256]; /* zones de travail pour */
UBYTE buffer_out[MAX_SIZE + 256]; /* treatment */

/* le pointeur de fonction operation doit recevoir l'adresse de la
 * fonction choisie par l'utilisateur : compress ou uncompress */
void (*operation)(UBYTE *, ULONG, UBYTE *, ULONG *) = NULL;

/* la fonction treatment() effectue le travail requis : compresser
 * ou decompresser le fichier dont le nom lui est passe dans input.
 * Le resultat de l'operation est place dans le fichier dont le nom
 * lui est passe dans output */
void treatment(char *input, char *output) {
	  int in, out;
	  ULONG length_in = 0;
	  ULONG length_out = 0;
	  /* Ouverture du fichier d'entrée en lecture (fichier a
	   * transformer) */
	  if ((in = open(input, O_RDONLY)) == -1) {
		    perror("Erreur ouverture fichier d'entrée");
		    exit(1);
	  }
	  /* Creation du fichier de sortie (resultat de la transformation) */
	  if ((out = open(output, O_WRONLY|O_CREAT, 0600)) == -1) {
		    perror("Erreur ouverture fichier de sortie");
		    exit(1);
	  }
	  /* lecture dans le fichier d'entrée du bloc de donnees a
	   * transformer */
	  length_in = read(in, buffer_in, sizeof(UBYTE)* MAX_SIZE);
	  /* Modification du buffer par appel de l'operation (compress ou
	   * uncompress) */
	  operation( buffer_in, length_in, buffer_out, &length_out);
	  /* Ecriture du buffer resultat dans le fichier de sortie */
	  write( out, buffer_out, sizeof(UBYTE)*length_out);
	  /* fermeture des fichiers d'entrée et de sortie */
	  close(in);
	  close(out);
}

/* La fonction loader() procede au chargement dynamique du contenu
 * d'un fichier objet designe par <function>.so, puis retourne
 * l'adresse virtuelle ou la <function>() a ete implantee */
void (* loader(char *function))(UBYTE*, ULONG, UBYTE*,ULONG*) {
	  char partageable[256]; /* zone pour le nom de l'objet partageable */
	  void *so_handle;
	  /* creation du nom de l'objet partageable*/
	  sprintf(partageable,"%s.so",function);
	  if ((so_handle = dlopen( partageable, RTLD_LAZY)) == NULL) {
		    fprintf(stderr, "Impossible to open %s: %s\n", partageable,
					  dlerror());
		    return NULL;
	  }
	  /* retour de l'adresse de la fonction chargee */
	  return (void (*) (UBYTE *, ULONG, UBYTE *, ULONG *))
		    dlsym( so_handle, function );
}
void usage(void);

/**
 * main : 
 **/
main(int argc, char **argv) {
	  if (argc < 4) usage();
	  /* Integration du fichier partageable choisi dans l'image memoire
	   * puis recuperation de l'adresse virtuelle de la fonction a
	   * executer. Placer ci-dessous le second argument de la ligne de 
	   * commande */
	  operation = loader( argv[1] );
	  //printf("Called function address : %p\n", operation);
	  /* Traitement du fichier "entrée" pour produire le fichier "sortie".
	   * Placer ci-dessous les 3eme et 4eme arguments */
	  treatment( argv[2], argv[3] );
}

void usage(void) {
		    fprintf(stderr, "Usage: %s operation f1 f2\n", argv0);
	  exit(1);
}
