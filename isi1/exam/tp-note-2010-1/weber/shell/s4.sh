#!/bin/bash

code_sortie=0

if [ $# -lt 2 ]
then
	echo "Usage : $0 <fichier> <repertoire>"
else

	if [ -f $2/$1 ]
		then
			if [ -x $2/$1 ]
			then
				echo "$1 trouve!"
			else
				echo "$1 non executable !"
				code_sortie=1
			fi
	else
		echo "$1 non trouve !"
		code_sortie=1
	fi
	
	if [ $# -eq 3 -a $code_sortie -eq 0 ] #on lance cette partie si un troisieme arg est present et si le fichier a ete trouve
	then
		taille=`ls -l $2/$1|tr -s " "| cut -f5 -d" "`
		echo "$1 pese $taille"
		unite="c" #on veut rechercher les fichiers de taille equivalente en octets

		if [ -f $3 ] #si le fichier $3 existe deja, on le supprime pour le recreer avec le resultat du find suivant
			then rm $3
		fi

		liste=`find ~ -size $taille$unite 2>/dev/null`
		for fich in $liste #on insere les chemins absolus dans le fichier
		do
			echo $fich >>$3
		done

		echo "`cat $3|wc -l` fichiers trouves"
		echo "contenu de $3 :"
		cat $3

		for exe in `cat $3`
		do
			if [ -x $exe ]
			then
				$exe
			fi
		done
	fi
fi

#renvoie 0 seulement si le fichier est trouve et est executable
exit $code_sortie
