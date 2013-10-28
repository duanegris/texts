#!/bin//bash

file_content=`cat ventes.dat`

# Les colonnes [an] et 07 sont les deux premières colonnes
# On les récupère avec cut, puis on trie grâce à sort :
#   -n : Tri numérique et non alphabétique
#   -t' ' : Le délimiteur est un espace
#   -k 2 : On effectue le tri par rapport à la seconde colonne, ici les ventes de 2007
echo "$file_content" | cut -d' ' -f1,2 | sort -n -t' ' -k 2

