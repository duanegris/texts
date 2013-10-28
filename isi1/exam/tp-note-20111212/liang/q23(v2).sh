#!/bin/bash
#sh q22.sh ventes.dat mois

selectfile=$1
value=$2
somme=0

cat $1 | grep "$2"
for((i=2;i<=6;i++))
do
somme=somme+`cat $1 | grep "$2" | cut -d ' ' -fi`
done
echo "$2 -> total: $somme"
