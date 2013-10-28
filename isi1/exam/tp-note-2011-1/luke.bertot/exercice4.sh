#!/bin/bash

dir=executable_`date +%d`_`date +%m`_`date +%y`;

sum=0;
if ([ -e /tmp/$dir ]) then
    cd /tmp/$dir;
    for i  in * 
    do
	taille=`wc -c $i |cut -d ' ' -f 1`;
	sum=$taille+$sum  ; #ne marche pas en l'état
    done
echo -n "taille totale : ";  
echo $sum;
else
    echo "/tmp/$dir n'existe pas";
fi

exit 0;