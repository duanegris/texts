#!/bin/bash

read -p "Saisir une ligne a ajouter au fichier : " ligne

echo $ligne >> ventes.dat

echo "Ligne ajoutee"
