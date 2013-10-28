#!/bin/bash

if [ $# != 2 ]; then
{
echo "usage: ./ <nom_programme> <operation> <valeur>"
exit 1
}
fi

case "$1" in
    sel )
echo "Sélection de la colonne $2"
echo -n "année : "
cut -d' ' -f $2 ventes.dat
echo "Nombre de lignes :"
cut -d' ' -f $2 ventes.dat | wc -l
;;
    som ) echo "Fonction somme"
echo "Je ne sais pas faire ça";;
    * ) echo "Mauvaise opération.";;
esac

exit 0