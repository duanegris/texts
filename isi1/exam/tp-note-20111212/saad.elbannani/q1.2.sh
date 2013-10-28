#!/bin/sh


fichier="ventes.dat"

echo "inserer nouvelle ligne"
read r

echo $r >> $fichier
