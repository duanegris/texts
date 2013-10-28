#!/bin/bash

fichiers=`ls ?.dat`

dateDuJour=`date` #a formater
for i in $fichiers
do
  if [ -f $i ]
  then
      rep=$i-$dateDuJour
      mkdir "$rep"
      cp $i "$rep/"
  fi
done