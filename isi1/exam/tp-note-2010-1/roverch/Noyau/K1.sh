#!/bin/bash

if [ $# -ne 1 ] ;then
	echo "Il faut entrer 1 parametre"
	exit 1

elif [ ! -f $1 ] ;then
	echo "Le fichier $1 n'est pas present"
	exit 1

else
	cat $1
	echo "Entrer le combre de character a lire"
	read rep
	if [ $rep -le 2^24 ] ;then
		echo "Erreur : nombre de character a lire superieur a 2^24"
	else
		write(1,$1,$rep)
	fi
fi