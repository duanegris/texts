#!/bin/bash

nom="";
nomentier=""
search=".sh"

for nom in `ls $1`
do 
    nomentier=$1/$nom
    if [ -d $nomentier ]
        then ./exo3_aux.sh $nomentier $2;	
	else(									# [SG] ( inutile
	    ext=`ls $1/$nom|tail -c4|cut -c1-3`
	    echo $ext;
	    if [ $ext==$search ] 
	        then echo $nomentier;
		     cp $nomentier $2
	    fi
           )
    fi
done