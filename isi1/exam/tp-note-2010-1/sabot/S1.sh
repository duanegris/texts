#!/bin/sh



if [ $# != 2 ]
then echo 'nombre de paramètres incorrect'
else if [ ! -d $2 ]
	then echo 'le deuxième paramètre doit être un répertoire'
        else if [ ! -f $2/$1 ]
                then echo ''$1' non trouvé'
                     exit 1
                else if [ -x $2/$1 ]
	                then echo ''$1' trouvé'
                        else echo 'non exécutable'
                     fi
                     exit 0
             fi
     fi
fi