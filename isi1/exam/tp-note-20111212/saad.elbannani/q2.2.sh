#!/bin/sh

nbarg=$*
op=$1
val=$2
fichier="ventes.dat"

 if [ $op != "sel" ]
then 
exit 1
else
cut  $fichier -f$val -d' ' | wc -l 
fi

