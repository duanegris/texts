#MAIRE
#!bin/bash

if [ $# != 2 ]
then
  echo "usage $0 arg1 arg2"
  exit
fi

if [ -d $1 ]&&[ ! -f $2 ]
then
  echo "Succes"
  exit
else
  echo "Echec : "
  if [ ! -d $1 ]
  then
    echo "$1 n'est pas un répertoire"
  fi
  if [ -f $2 ]
  then
    echo "$2 est un fichier"
  fi
  exit
fi
