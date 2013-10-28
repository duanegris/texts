# BRACIGLIANO

#!/bin/bash
# manque la condition sur fichier special

if [ $# -ne 2 ]
then echo "usage $0 arg1 arg2"
exit 1
fi

if [ -d $1 ]
then if [ -f $2 ] || [ -d $2 ]
	then "$2 ne doit ni �tre un fichier, ni un r�pertoire, ni un fichier special"
	exit 2
	else echo "succes"
	exit 0
	fi
else echo "$1 doit �tre un r�pertoire"
	exit 2
fi