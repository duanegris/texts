#!/bin/bash

nom="executable_"`date +%d`"_"`date +%m`"_"`date +%y`
res=0;

for fichier in `ls /tmp/$nom`
do
    res=$(($res+`ls -l $fichier|cut -d' ' -f5`)) #[SG] pb : manque le /tmp/$fichier

done

echo "La somme des tailles vaut "$res;