#!/bin/bash

datafile="ventes.dat"


echo "Entrez une nouvelle ligne :"
read
echo "Vous avez ajouté :"
echo "$REPLY"
echo "$REPLY" >> $datafile
echo "Le fichier contient maintenant :"
cat $datafile

