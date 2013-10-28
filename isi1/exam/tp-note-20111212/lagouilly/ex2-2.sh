#! /bin/sh


if [ $# -gt 2 ]; then 
	echo "Erreur : vous avez entré plusieur parametres... Veuillez corriger."
	exit 1;
else 
	if [ $# -eq 2 ]; then
		if [ $1 -eq 'sel' ]; then
                        cut -d" " -f$2 ventes.dat
	fi
fi

