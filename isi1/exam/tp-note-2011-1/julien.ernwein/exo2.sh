#!/bin/bash

valretour=0

if [ $# != 2 ]
then
	echo "usage $0 arg1 arg2"
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
		touch $arg2
		#chmod 777 $arg2
		chmod a+x $arg2
		echo echo "Bienvenue" > $arg2    # commandes en double
		echo date >> $arg2				 # [SG] faire `date`
		echo echo $USER >> $arg2
	else
		echo "$arg2 est un fichier !"
		valretour=2
	fi
else
	echo "$arg1 n'est pas un répertoire existant dans `pwd` !"
	valretour=2
fi
exit $valretour
