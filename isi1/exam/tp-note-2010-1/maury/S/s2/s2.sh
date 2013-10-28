#!/bin/sh

k=0
if [ $#==2 ]
then 
    cd $2
    if [ -e $1 ]
    then
	    #s2
	if [ $#==3 ]
	then
	    echo "$1 taille :" `ls -l | grep $1 | cut -f5 -d' '`
	fi
	
	if [ -x $1 ]
	then
		echo " $1 trouve "
		k=1
	else
	    echo " $1 non executable "
	fi
    else
	echo " fichier non trouve "
    fi
else 
    echo " parametres non conformes"
fi
echo " $k "
