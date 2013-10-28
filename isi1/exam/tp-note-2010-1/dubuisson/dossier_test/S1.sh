#!/bin/bash

if [ $# -ne 2 ]; then
	echo "Le nombre d'arguments est incorrect. Veuillez recommencer."
else
	if [ -f $2/$1 ]; then
		if [ -x $2/$1 ]; then
			echo "$1 trouve"
		else
			echo "$1 trouve, non executable"
		fi
		exit 0
	else
		echo "$1 non trouve"
		exit 1
	fi	
fi	
