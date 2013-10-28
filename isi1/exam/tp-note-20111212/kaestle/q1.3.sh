#!/bin/bash

fichiers=`find -name '*.dat' -printf '%f\n'`

for fichier in $fichiers
do
	dir="$fichier-`date --rfc-3339='date'`"
	mkdir $dir
	cp $fichier "$dir/$fichier"
	 
done

