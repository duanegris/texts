#!/bin/bash
#sh q22.sh ventes.dat mois

selectfile=$1
value=$2

cat $1 | grep "$2"

echo "$2 -> total: `cat $1 | grep "$2" | cut -d ' ' -f2,3,4,5,6 | wc -w`"
