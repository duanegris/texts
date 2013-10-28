#!/bin/sh

k=0
if [ $#==2 ]
then 
    cd $2
    if [ -f $1 ]
    then
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
    echo " parametres non conformes "
fi
echo " $k "