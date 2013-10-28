#!/bin/bash

code_sortie=0

if [ $# -ne 2 ]
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

fi

#renvoie 0 seulement si le fichier est trouve et est executable
exit $code_sortie
