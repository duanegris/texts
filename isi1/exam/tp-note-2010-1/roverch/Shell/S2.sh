#!/bin/bash

#il manque le fonction permettant de trouver la taille d'un fichier...

if [ $# -lt 2 -a $# -gt 3 ] ;then
	echo "Il faut entrer 2 ou 3 parametres"
	exit 1

elif [ ! -d $2 ] ;then
	echo "le second parametre doit etre un dossier"
	exit 1

elif [ -f $2/$1 -a $# -eq 2 ] ;then
	if [ -x $1 ] ;then
		echo "$1 est un executable"			
		exit 0
	else
		echo "$1 n'est pas un executable"
		exit 0
	fi

elif [ ! -f $2/$1 -a $# -eq 2 ] ;then
		echo "$1 n'est pas dans le repertoire $2"
		exit 1

elif [ -f $2/$1 -a $# -eq 3 ] ;then
	if [ -x $1 ] ;then
		echo "$1 est un executable, sa taille est de $n :"
		ls -l | grep "$2/$1" | tr -s ' ' | cut -f5 -d' '
		exit 0
	else
		echo "$1 n'est pas un executable, sa taille est de $n :"
		ls -l | grep "$2/$1" | tr -s ' ' | cut -f5 -d' '
		exit 0
	fi

else
	echo "$1 n'est pas dans le repertoire $2"
fi