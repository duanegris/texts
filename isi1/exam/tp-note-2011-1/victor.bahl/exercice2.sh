#! /bin/bash

if [ $# -ne 2 ]
then
	echo "usage $0 arg1 arg2"
	exit 1
fi

arg1=$1
arg2=$2

uti=`ps -aux | grep $0 | tr -s " " | cut -d" " -f1 | uniq` > /dev/null # [SG] redir inutile
# Affiche un warning (que je n'ai pas réussi à enlever) 

if [ -d $arg1 ]
then
	
	if [ -f $arg2 ]
	then
		echo "Erreur : $arg2 est un fichier"
		exit 2
	else
		echo "Succes"
		
		# Partie exercice 2
		
		touch $arg2
		chmod +x $arg2
		echo "#! /bin/bash" > $arg2
		echo "echo \"Bienvenue\" " >> $arg2
		echo "date | cut -d\" \" -f1-4 " >> $arg2
		echo "echo \"$uti\" " >> $arg2
		
		
		exit 0
	fi

else
	echo "Erreur : $arg1 n'est pas un repertoire"
	exit 2
fi
	
