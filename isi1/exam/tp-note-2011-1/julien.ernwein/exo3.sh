#!/bin/bash

jj=`date +%d`
mm=`date +%m`
aa=`date +%C`

rep="/tmp/executable_"$jj"_"$mm"_"$aa

if [ -d $rep ]
then
	echo "Le répertoire $rep existe déja !" 
else
	mkdir $rep
	chmod g+rw $rep
fi

for fichier in `find . -name "*.exe"`
do
	cp $fichier $rep
	echo "Copie du fichier $fichier" 
done

#find . -name "*.exe" -exec cp ....
