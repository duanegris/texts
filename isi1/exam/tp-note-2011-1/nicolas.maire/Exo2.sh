#!bin/bash

if [ $# != 2 ]
then
  echo "usage $0 arg1 arg2"
  exit
fi

if [ -d $1 ]&&[ ! -f $2 ]
then
  echo "Succes"
  touch $2
  chmod +x $2
  echo "#!bin/bash" >> $2
  echo "" >> $2
  D=`date`
  U=`whoami`
  echo -n "Bienvenue $D $U" >> $2
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
