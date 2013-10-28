# ERNWEIN

#!/bin/bash

valretour=0

if [ $# != 2 ]
then
	echo "usage $0 arg1 arg2"  # bien
	valretour=1	
	exit $valretour
fi

arg1=$1
arg2=$2

if [ -d $arg1 ]
then
	if [ ! -e $arg2 ]
	then
		echo "succes"			
	else
		echo "$arg2 est un fichier (fichier régulier, repertoire ou repertoire spécial) !"
		valretour=2
	fi
else
	echo "$arg1 n'est pas un répertoire existant dans `pwd` !"
	valretour=2
fi

exit $valretour
