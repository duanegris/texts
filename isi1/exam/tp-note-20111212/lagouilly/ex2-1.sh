#! /bin/sh


if [ $# -gt 2 ]; then 
	echo "Erreur : vous avez entré plusieur parametres... Veuillez corriger."
	exit 1;
else 
	if [ $# -eq 2 ]; then
		echo "bon nombre d'argument"
                exit 0;
	fi
fi

