#!/bin/bash

if [ $# -ne 2 ]
then echo "usage $0 arg1 arg2"
exit 1
fi

if [ -d $1 ]
then if [ -f $2 ] || [ -d $2 ]
	then "$2 ne doit ni �tre un fichier, ni un r�pertoire, ni un fichier special"
	exit 2
	else 	touch $2
			chmod 777 $2
			date=`date +"%d %B %Y"`
			personne=`who -m | cut -d' ' -f1`
			echo "Bienvenue $date $personne" > $2
	exit 0
	fi
else echo "$1 doit �tre un r�pertoire"
	exit 2
fi