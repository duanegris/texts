#!/bin/bash

if [ $# -lt 2 ]; then
	echo "Le nombre d'arguments est incorrect. Veuillez recommencer."
else
	if [ -f $2/$1 ]; then
		if [ -x $2/$1 ]; then
			echo "$1 trouve"
		else
			echo "$1 trouve, non executable"
		fi
			if [ $# -eq 3 ]; then
				temp=`ls -l $2/$1 | awk '{ print $5 }'`
				echo "$1 est de taille : $temp o"

				`find -size ${temp}c > $3`
				compt=`wc -l $3 | awk '{ print $1 }'`

				for path in `find -size ${temp}c`; do
					if [ -x $path ]; then
						./$path
					fi	
				done	

				echo "$compt fichiers ont ete trouve"
				more $3
			fi	
		exit 0
	else
		echo "$1 non trouve"
		exit 1
	fi	
fi	