#!/bin/sh

if [ $# -eq 2 ]
then
	dossier="$1"
	res=$2
elif [ $# -eq 0 ]
then
	dossier="."
	date=`date | tr -s " " | cut -d" " -f1,2,3`
	echo "Bienvenue $date $USER"

	jj=`date +%d`
	mm=`date +%m`
	aa=`date +%y`

	un="executable_$jj"
	deux="_$mm"
	trois="_$aa"
	res=$un$deux$trois
	mkdir /tmp/$res
	chmod g+rw /tmp/$res
else
	echo "usage $0 Option : arg1 arg2"
	exit 1
fi



for fichier in `ls $dossier/*.exe`
do
	if [ ! -d $fichier ]
	then
		if [ $dossier = "." ]
		then
			cp $fichier /tmp/$res
		else
			cp ./$fichier /tmp/$res
		fi
	fi
done

for rep in `ls`
do
	if [ -d $rep ]
	then	
		if [ $dossier = "." ]
		then
			sh $0 $rep $res
		fi	
	fi
done

if [ $dossier = "." ]
then
	echo `du /tmp/$res | tr -s " " | cut -d" " -f1`
fi
