#!/bin/bash

j=0
for i in `cat ventes.dat | cut -d" " -f2,3,4,5`
do
  if [ $j -le 3 ]  
  then  mkdir "$i".dat
  j=`expr $j + 1`
  fi
done

#Au dessus j'ai que la création de dossier et j'etais en train d'essayer de mettre les resultats dans chaque fichiers


# nbr=6;#constante = nbr de colonnes
# i=1
# ligne=6 #constante = nbr de lignes
# ligneActu=1
# while [ $i -le $nbr ]
# do
#   for j in `cut -d" " -f$i < ventes.dat`
#   do  
#     if [ $ligneActu -e $ligne ]
#       then ligneActu=1
#     fi
# 
#     if [ $ligneActu -e 1 ]
#     then 
# 	    tmpDir="'$j'.dat" 
# 	    mkdir $tmpDir
#     else $j>>$tmpDir
#     fi
# 
#   ligneActu=`expr $ligneActu + 1`
#   done
# i=`expr $i + 1`
# done