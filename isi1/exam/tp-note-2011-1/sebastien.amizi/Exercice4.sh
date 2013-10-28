#!/bin/bash

rep="/tmp/executable_`date +%d`_`date +%m`_`date +%y`"
tailles=`ls $rep -l | tr -s ' ' |  cut -d ' ' -f5`
total=0
for valeur in $tailles
do
    total=`expr $total + $valeur`
done
echo $total
