#!/bin/bash

FILE=ventes.dat

usage()
{
  echo "Usage: $0 <Operation> <Valeur>"
  exit -1
}

# Vérification du nombre d'arguments
if [ $# -ne 2 ]
then
  usage $0
fi

# Vérification de l'opération qui doit être sel ou som
if [[ "$1" != 'sel' && "$1" != 'som' ]]
then
  echo "L'operation doit être 'sel' ou 'som'."
  usage $0
fi

# Si l'opération est sel, on veŕifie la structure de la valeur : un nombre
if [[ "$1" == 'sel'  && -z `echo "$2" | grep -E "[0-9]+"` ]]
then
  echo "'sel' > La valeur doit être un nombre."
  usage $0
fi
  
# Si l'opération est som, on vérifie la structure de la valeur : 4 caracères minuscules
if [[ "$1" == 'som'  && -z `echo "$2" | grep -E "[a-z]{4}"` ]]
then
  echo "'som' > La valeur doit être les 4 premières lettres d'un mois de l'année."
  usage $0
fi

content=`cat $FILE`

case $1 in
  'sel')
    column=`echo "$content" | cut -d' ' -f $2`
    if [ -z "$column" ]
    then
      echo "Pas de colonne ayant cet index"
    else
      echo "$column"
      echo "Nombre de lignes : `echo "$column" | wc -l`"
    fi
    ;;

  'som')  
    line=`echo "$content" | grep "$2"`
    if [ -z "$line" ]
    then
      echo "Pas d'entrée avec le moi $2"
    else
      # On parcourt la ligne en enlevant un élément à chaque fois pour l'ajouter à la somme
      list_value=`echo "$line" | cut -d' ' -f2-`
      som=0
      for value in $list_value
      do
        som=`expr $som + $value`
      done
      echo "$2 → total: $som"
    fi
    ;;
esac
