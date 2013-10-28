#!/bin/bash

datafile="ventes.dat"
nbcol=5

for((i=2 ; i<$nbcol+2 ; i++ ))
do
	echo `cat ventes.dat | cut -d' ' -f$i` > mois$i
	chmod -w mois$i
done

