#!bin/bash

annee=`date --rfc-3339=date | cut --characters=3-4`
mois=`date --rfc-3339=date | cut --characters=6-7`
jour=`date --rfc-3339=date | cut --characters=9-10`

dossier="/tmp/executable_"$jour"_"$mois"_$annee"

mkdir $dossier

chmod g+rw $dossier

for i in *
do
  if [ -d $i ]
  then
    cd "$i"
    #Partie récursive à faire
  elif [ -f $i ]
  then
    cp $i "$dossier/$i"
  fi
done
