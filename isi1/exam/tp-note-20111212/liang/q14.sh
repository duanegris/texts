#!/bin/bash

#sh q14.sh ventes.dat
declare -i
for ((i=1;i<7;i++))
do
grep $1 | cut -d ' ' -f i | mkdir i.dat 
done