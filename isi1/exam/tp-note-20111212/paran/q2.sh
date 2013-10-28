#!/bin/bash

erreur() {
	if [ $# != 2 ]; then
		echo "Erreur : $1"
		echo "usage : $0 <operation> <valeur>"
		exit 1
	fi
}

OPE=$1
VAL=$2

COL=`grep janv ventes.dat | wc -w`


if [ $# -ne 2 ]; then
	erreur "Nombre d'arguments"
fi

if [ $OPE != "sel" -a $OPE != "som" ]; then
	erreur "Operateur invalide"
fi


case $OPE in
	sel) 
		if [ $VAL -lt 0 ] || [ $VAL -gt $COL ]; then
        		erreur "Valeur invalide"
		fi

		cat ventes.dat | cut -d " " -f$VAL     
	;;

	som) 
		if [ $VAL != "janv" -a $VAL != "fevr" -a $VAL != "mars" -a $VAL != "avri" -a $VAL != "dece" ]; then
        		erreur "Valeur invalide"
		fi
		
		# Pas eu le temps de faire la somme	
		total=`grep $VAL ventes.dat | cut -c 6-`
		echo "$VAL -> total: $total"
	;;
esac

