#!/bin/bash

if [$1="" || $2=""]
then echo "usage <exo1> $1 $2";
echo "1";
fi

if [-d $1 && -f $2]
then echo "condition non satisfaite";
echo "2"; 
else if [-d $2]
then echo "condition non satisfaite";
echo "2";
else echo "succes";
echo "0";#!/bin/bash

if [$1="" || $2=""]
then echo "usage <exo1> $1 $2";
echo "1"
fi

if [-d $1 && -f $2]
then echo "succes";
echo "0";
do `touch $2.sh`
do `chmod +x ugo $2.sh`
date=`date`                  
echo "nom de l'utilisateur?"
read $nom
echo <date><nom>

else echo "condition non satisfaite";
echo "2";
fi
