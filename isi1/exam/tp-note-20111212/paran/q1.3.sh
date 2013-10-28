#!/bin/bash

DIR=$1-`date +%d-%m-%Y`
FILE=$1.dat

if [ ! -f $FILE ]; then
	echo "Erreur : $1.dat n'existe pas ou n'est pas un fichier regulier."
	exit
fi

mkdir $DIR

cp $FILE $DIR
