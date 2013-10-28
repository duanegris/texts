#!/bin/bash

datedujour=`date +"%d_%m_%y"`

cd /tmp/executable_$datedujour

SOMME=0

for i in `ls -l`
do
    SOMME=$((`ls -l | cut -d' ' -f5`+$SOMME))
done

echo "La somme des fichiers vaut : $SOMME"
