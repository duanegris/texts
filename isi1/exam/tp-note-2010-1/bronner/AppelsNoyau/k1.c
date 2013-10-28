#include <stdio.h>
#include <unistd.h>
#include <stdio.h>

int main()
{
  int SSIZE_MAX = 200000 ;
  char *fichier = "test";
  int lu;
  char *f;

  f = fopen(fichier, "r");   //@@SG warning type incompatible retour ignoree

  read(0, lu , SSIZE_MAX);   //@SG suite d'octets mise dans 1 entier

  write(1, f, lu);           //@SG incohérent car f est le FILE * retourné

  fclose(f);

  return 0 ;
}




