#!/bin/sh


if [ $# = 2 ]
then

    if [ -d $2 ]
    then
	echo `ls -l $2 | grep $1`
    else
	echo "Les types des fichiers ne sont pas corrects"
    fi
    
else
    echo "Nombre de parametres incorrect"
fi