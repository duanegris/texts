#!/bin/bash

#test de la conformite des paramatres passes
if [ $# != 2 ] ; then 
    echo "il n y a pas le bon nombre de parametres" ;
fi

if [ '-d $2' == 0 ] ; then
    echo "le second parametre n est pas un repertoire" ;
fi

if [ '-f $1' == 0  ] ; then
    echo "le premier parametre n est pas un fichier" ;
fi

#recherche du fichier
if [ 'ls $2|grep -c $1' >= 1 ] ; then
    if [ -x $1 ] ; then
	echo "$1 trouve" ;
    else
	echo "$1 non executable" ;
    fi
    1 ;    
else
    
    set reponse = 0 ;
    
    for courant in 'ls $2' ; do
	set reponse = './S1.sh $1 $courant' || $reponse ;
    done ;

    if [ $reponse ] ; then
	1 ;
    else
	echo "$1 non trouve" ;
	0 ;
    fi
fi