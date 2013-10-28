#!/bin/sh


fichier="ventes.dat"
j=6
i=1
while [ $i -ne $j ]

do
cut  $fichier -f$i -d' ' > $i.dat umask 000
i=$((i+1))
done
