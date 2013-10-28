#! /bin/bash

if [ $# != 2 ]
then
	echo "usage     : ./q2.1.sh <operation> <valeur>"
	echo "operation : sel ou som"
	echo "valeur    : c ou m'"
	exit 1
else
a=`cat ventes.dat | cut -d"
" -f1 | cut -d" " -f2-`
nb_mois=`echo $a | wc -w`

les_mois=`cat ventes.dat | cut -d" " -f1`

action=$1
colone=$2

le_mois_existe=`cat ventes.dat | cut -d" " -f1 | grep $colone | wc -l`

	if [ $action == "sel" ]
	then
		if [ $nb_mois -ge $colone ] && [ $colone -gt 0 ]
		then
			res=`cat ventes.dat | cut -d" " -f$colone`
			echo "Les valeurs : "$res
			echo "On a "$((`echo $res | wc -w`-1))" lignes pour cette colone"
		else
			echo "Cette colone n'existe pas, elles varient ici entre 1 et "$nb_mois
		fi
	else
		if [ $le_mois_existe -gt 0 ]
		then
			res=`cat ventes.dat | grep $colone | cut -d" " -f2-`
			sum=0
			for i in $res
			do
				sum=$((sum+$i))
			done
			echo "Valeurs : "$res
			echo "La somme du mois de "$colone" est donc de "$sum" objets vendus."
		else
			echo "Ce mois n'existe pas"
		fi
	fi
fi
