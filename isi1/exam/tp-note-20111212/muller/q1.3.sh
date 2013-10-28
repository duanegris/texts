#! /bin/bash

a=`ls | grep [a-z0-9]".dat"`

ladate=`date +%d-%m-%Y`

for i in $a
do
	nom=`echo $i | cut -d"." -f1`
	taille_nom=$((`echo $nom | wc -c`-1))
	if [ taille_nom == 1 ]
	then
		mkdir nom-ladate
		cp nom".dat" nom/
	fi
done
