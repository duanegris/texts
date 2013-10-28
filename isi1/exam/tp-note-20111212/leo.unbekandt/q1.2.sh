#!/bin/bash

filename=ventes.dat

echo "Veuillez entrer une nouvelle ligne : "

# On lit ce que nous donne l'utilisateur
read ligne

# Et on l'ajoute simplement au fichier ventes.dat
echo $ligne >> ventes.dat
