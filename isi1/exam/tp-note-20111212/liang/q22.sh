#!/bin/bash
#sh q22.sh colonne

selectfile=$1
value=$2

cat $1 | grep [a-z] | cut -d ' ' -f$2 

echo "Le somme est: `cat $1 | grep [a-z] | cut -d ' ' -f$2 | wc -l`"