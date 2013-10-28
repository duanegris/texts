# FAUGEROUX

#!/bin/bash

if [$1="" || $2=""]
then echo "usage <exo1> $1 $2";  # mieux : [ $# -ne 2 ]
echo "1"   # exit !
fi


if [-d $1 && -f $2]    # non condition contraire sur $2
then echo "succes";    
echo "0";
else echo "condition non satisfaite";
echo "2";
fiif [-d $1 && -f $2]   # syntaxe !!
then echo "succes";
echo "0";
else echo "condition non satisfaite";
echo "2";
fi




