#!/bin/sh

if [ $# -ne 2 ]
then
	echo "usage $0 arg1 arg2"
	exit 1
fi

date=`date | tr -s " " | cut -d" " -f1,2,3`
echo "Bienvenue $date $USER"

res=0

if [ -d $1 ]
then
	
	res=1
else
	echo "$1 n'est pas un répertoire"
fi

if [ ! -d $2 ]
then
	if [ ! -f $2 ]
	then
		res=1
	else
		res=0
		echo "$2 est un fichier"
	fi
else
	res=0
	echo "$2 est pas un répertoire"	
fi

if [ $res -eq 1 ]
then
	echo "succes"
	touch $2
	chmod a+x $2	
fi



