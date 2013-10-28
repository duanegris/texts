# BRACIGLIANO

#!/bin/bash
# manque la condition sur fichier special

if [ $# -ne 2 ]
then echo "usage $0 arg1 arg2"
exit 1
fi

if [ -d $1 ]
then if [ -f $2 ] || [ -d $2 ]
	then "$2 ne doit ni être un fichier, ni un répertoire, ni un fichier special"
	exit 2
	else echo "succes"
	exit 0
	fi
else echo "$1 doit être un répertoire"
	exit 2
fi