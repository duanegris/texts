#!/bin/sh

colo=`cat ventes.dat | cut -d' ' -f1`

com=1

while [ colo > 0 ]

do 
    num=`echo colo| cut -d' ' -f1`
    new_fich=`mkdir <num>.dat`
    cp $colo ventes.dat $new_fich
    com=$(($com+1))
    colo=colo=`cat ventes.dat | cut -d' ' -f$com`
done
