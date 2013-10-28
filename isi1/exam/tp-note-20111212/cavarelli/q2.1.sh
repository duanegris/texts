#! /bin/bash

ope=$1
val=$2

if [ $# != "2" ] 
	do
	echo "Le nombre d'arguments est mauvais"
	done
else if [ $1 = "sel" ] && [ 0 < $2 < 7 ]
		do
		cat ventes.dat | cut -d " " -f1,2
		var= `cat ventes.dat | cut -d " " -f1,2 | wc -l`
		echo "Nombre de lignes :$var"
		done		
	else 
		do
		echo "Problème dans les arguments"
		done
else if [ $1 = "som" ] && [ 0 < $2 < 13 ]
		do

		done
	else 
		do
		echo "Problème dans les arguments"
		done
elif

