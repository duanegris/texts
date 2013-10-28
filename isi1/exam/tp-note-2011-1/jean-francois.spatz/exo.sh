#SPATZ

#!/bin/sh




if [ $# -ne 2 ]
then
	echo "usage $0 arg1 arg2"
	exit 1
fi

res=0

if [ -d $1 ]
then

	res=1
else
	echo "$1 n'est pas un répertoire"  # il faudrait exit 2
fi

if [ ! -d $2 ]		# logique inversée "sucess" si [ -d $2 ] && ![ -f 2]
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
fi
