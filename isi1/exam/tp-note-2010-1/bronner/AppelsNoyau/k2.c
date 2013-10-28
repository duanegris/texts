
#include <stdio.h>
#include <unistd.h>
#include <stdio.h>

int main()
{
  int SSIZE_MAX = 200000 ;
  char *fichier = "test";
  int ent1, ent2;
  char *f;

  f = fopen(fichier, "r");

  read(0, ent1, SSIZE_MAX);    //@@SG pas scanf() ? ...
  read(0, ent2, SSIZE_MAX);    //@@ ... et pas &ent2 ? pas compris lecture prototype read() dans man

  fseek(f, ent2, SEEK_SET);

  write(1, f, ent1);           //@@ avant d'écrire, il faut lire f

  fclose(f);

  return 0 ;
}


