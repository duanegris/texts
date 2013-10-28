#!/bin/bash

SOMME=0
for i in $*
do if [ $0 = "executable_jj_mm_aa" ]
   then SOMME += [`wc -c $i*`]
   fi
done

echo "la somme des tailles est de `SOMME`"
			












