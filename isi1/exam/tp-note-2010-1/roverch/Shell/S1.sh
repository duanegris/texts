#!/bin/bash

if [ $# -ne 2 ] ;then
	echo "Il faut entrer 2 parametres"
	exit 1

elif [ ! -d $2 ] ;then
	echo "le second parametre doit etre un dossier"
	exit 1

else
	if [ -f $2/$1 ] ;then
		if [ -x $1 ] ;then
			echo "$1 est un executable"
			exit 0
		else
			echo "$1 n'est pas un executable"
			exit 0
		fi
	else
		echo "$1 n'est pas dans le repertoire $2"
		exit 1
	fi
fi