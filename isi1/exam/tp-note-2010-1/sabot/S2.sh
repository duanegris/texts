#!/bin/sh




if [ $# -lt 2 ]
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
                     if [ $# = 3 ]
			 then echo $1
			 ls -l $2 | grep $1 | awk '{print $5}'
		     fi
                     exit 0
             fi
     fi
fi