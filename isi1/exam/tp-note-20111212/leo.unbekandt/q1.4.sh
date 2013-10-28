#!/bin/bash

file_content=`cat ventes.dat`
i=1
column=`echo "$file_content" | cut -f"$i" -d' '`

# On parcourt toutes les colonnes que nous donne le cut
# Jusqu'à ce que ce que la commande ne noue renvoie plus rien
while [ -n "$column" ]
do
  # On place le contenu dans la colonne dans le fichier correspondant
  echo "$column"  >> "$i.dat"
  # On enlève les droit d'écriture
  chmod a-w "$i.dat"

  # On incrémente i pour récuperer la prochaine colonne (s'il y en a une)
  i=`expr $i + 1`
  column=`echo "$file_content" | cut -f"$i" -d' '`
done
