#!/bin/bash

datafile="ventes.dat"

if [ $# -ne 2 ] ; then
	echo "usage: ./exo2.sh <operation> <valeur>"
	exit 1
fi

if [ $1 != "sel" ] && [ $1 != "som" ] ; then
	echo "usage operation: sel | som"
	exit 1
fi

case $1 in
	"sel")
		echo "Selection d'une colonne"
		cat $datafile | cut -d' ' -f$2
	;;
	"som")
		echo "Sommation du mois"
	;;
esac

