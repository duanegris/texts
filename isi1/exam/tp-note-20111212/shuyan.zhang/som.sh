#!/bin/sh

mois=$1

vent=`grep $mois ventes.dat`

prem=2

elem=`echo $vent|cut -d' ' -f$prem`

while ( elem > 0 )

do 

sum=$(($sum+elem))

prem=$(($prem+1))

elem=`echo $vent|cut -d' ' -f$prem`

done

echo $sum




