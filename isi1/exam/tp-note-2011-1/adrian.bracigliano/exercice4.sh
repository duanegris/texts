#!/bin/bash


date=`date +"%d_%m_%y"`
repertoir="executable_$date"
cd /tmp
	if [ -d $respertoir ]
	then
		echo "le répertoire existe déja"
	else
		mkdir $repertoir
		chmod g+rw $repertoir
	fi
	
# je sous entend que le dossier est rempli ici...

cd /$repertoir
	
	somme=0
	nombre=`ls -l | tr -s " " | cut -d' ' -f5` # on récupère le nombre binaire
	for i in $nombre
	do
		somme=$(($somme+$i))
	done
	echo "La somme des tailles en octects des fichiers est $somme"
	