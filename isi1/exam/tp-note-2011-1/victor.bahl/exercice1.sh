# BAHL

#! /bin/bash

if [ $# -ne 2 ]
then
	echo "usage $0 arg1 arg2"
	exit 1
fi

arg1=$1
arg2=$2

if [ -d $arg1 ]
then
	
	if [ -f $arg2 ]
	then
		echo "Erreur : $arg2 est un fichier"
		exit 2
	else
		echo "Succes"
		exit 0
	fi

else
	echo "Erreur : $arg1 n'est pas un repertoire"
	exit 2
fi
	
