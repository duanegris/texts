#!/bin/bash


echo "Entrez le mois";
read mois;
echo "Entrez les ventes faites en 07,08,09,10,11" #on aurait pu faire une boucle a la place 
read v07
read v08
read v09
read v10
read v11;
echo "\n$mois $v07 $v08 $v09 $v10 $v11">>ventes.dat; #le \n sert a mettre a la ligne pour conserver l'indentation