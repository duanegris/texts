#! /bin/bash

var=""
echo "Ligne de données à ajouter dans le fichier $1 :"
read var
echo "$var" >> $1

