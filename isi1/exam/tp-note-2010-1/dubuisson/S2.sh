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
			fi	
		exit 0
	else
		echo "$1 non trouve"
		exit 1
	fi	
fi	
