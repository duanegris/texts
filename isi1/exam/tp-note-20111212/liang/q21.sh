#!/bin/bash
#sh q21.sh ventes.dat sel|som data

selectfile=$1
operation=$2
value=$3
somme=0
declare -i x
case $2 in

"sel")
cat $1 | grep [a-z] | cut -d ' ' -f$3
break;;
"som")
for((x=2;x<=6;x++))
do
#cat $1 | grep "$3" | cut -d ' ' -f2,3,4,5,6
somme=somme+`cat $1 | grep "$2" | cut -d ' ' -fx`
done
break;;
"*")
echo "Error!"
break;;
esac 
