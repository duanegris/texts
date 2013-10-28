#!/bin/bash

if [ $# -ne 2 ]; then
    echo "usage $0 arg1 arg2"
else
    if [ -d $1 -a ! -f $2 ]; then
        touch $2
        chmod +x $0	# [SG] non, c'est $2
        date_du_jour=`date | cut -d ',' -f1`
        nom_de_lutilisateur_qui_le_lance=`who | sort | cut -d ' ' -f1 | uniq -c | tr -s ' ' | cut -d' ' -f3`
        echo "Bienvenue $date_du_jour $nom_de_lutilisateur_qui_le_lance"
    else
        echo "Echec!"
    fi
fi
