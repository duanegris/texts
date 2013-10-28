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
		echo "$1 pese `ls -l $2/$1|tr -s " " | cut -f5 -d" "`"
	fi
fi

#renvoie 0 seulement si le fichier est trouve et est executable
exit $code_sortie
