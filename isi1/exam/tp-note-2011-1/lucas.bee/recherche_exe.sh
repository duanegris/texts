#!/bin/bash

datedujour=`date +"%d_%m_%y"`
place1=`pwd`

if [ -d $1 ]
then
    cd $1
    for i in `ls | grep *.exe`
    do
    cp $i /tmp/executable_$datedujour 
    done
else echo "erreur d'argument"
fi

for j in `ls`
do
    if [ -d $j ]
    then
        echo "on recommence dans $j"
        `~/Bureau/Lucas_BEE/recherche_exe.sh ./$j`
    fi
done
