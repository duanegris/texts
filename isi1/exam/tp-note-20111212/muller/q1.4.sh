#! /bin/bash

a=`cat ventes.dat | cut -d"
" -f1 | cut -d" " -f2-`

echo $a

b=`echo $a | wc -w`

echo $b

for i in `seq 1 $b`
do
	nom=`echo $a | cut -d" " -f$i`
	cat ventes.dat | cut -d" " -f$i > $nom".dat"
	chmod -w $nom".dat"
done
