#!/bin/bash

if [ $# -ne 2 ]
then
	echo "usage: $0 <file> <directory>"
	exit -1;
fi

for i in $(ls $2)
do
	if [ $1 = $i ]
	then
		if [ -x $1 ]
		then
			echo "$1 trouvé"
		else
			echo "$1 non exécutable"
		fi
		exit 0
	fi
done

echo "$1 non trouvé"

exit 1