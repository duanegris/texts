#!/bin/bash

#test arguments
if [ $# -ne 2 ] 
then echo "erreur 0 : usage: ./<nom_programme> <operation> <valeur>"
     exit 0
fi

if [ $1 = "sel" ]
then 
     echo `cut -d" " -f$2 < ventes.dat `
     nbr=`cut -d" " -f$2 < ventes.dat | wc -l`
     echo `expr $nbr - 1` "lignes"

elif [ $1 = "som" ]
then 
     total=0
     liste=`grep $2 < ventes.dat `
     for i in $liste
     do
	if [ $2 != $i ]
	then
	  total=`expr $i + $total`
	fi
    done
    echo $2 "-> total : "$total
fi

 