#!/bin/bash

# En parcourant les fichiers avec *.dat on n'aura pas de souci avec les noms de fichiers contenant des espaces
# Si on avait utilisé ls par exemple, il y aurait eu des soucis avec les noms de fichiers.
for file in *.dat
do
  # On récupère le nom sant l'extension pour vérifier la longueur de son nom
  base_name=`basename "$file" .dat`
  length_name=`expr length "$base_name"`

  # Si le fichier et régulier et que son nom (sans l'extension) a une longueur de 1
  if [[ -f "$file"  && $length_name -eq 1 ]]
  then
    # On crée un dossier ayant le nom du fichier suivit de la date
    dir_name=$base_name-`date +%d-%m-%Y`
    mkdir "$dir_name"
    # Et on copie le fichier dans le dossier
    cp -v "$file" "$dir_name/$file"
  fi
done
