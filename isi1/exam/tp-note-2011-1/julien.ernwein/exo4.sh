#!/bin/bash

jj=`date +%d`
mm=`date +%m`
aa=`date +%C`

rep="/tmp/executable_"$jj"_"$mm"_"$aa
taille=0

for i in `ls -l $rep | tr -s ' ' | cut -d' ' -f5`
do
taille=`expr $taille + $i`
done

echo "Le repertoire $rep contient $taille octet(s)"

