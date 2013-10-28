#!/bin/bash

if [ $# -lt 2 ]
then
	echo "usage: $0 <file> <directory> [liste]"
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
			
		if [ $# -eq 3 ]; then
			echo $1 $(du -b $1 | cut --fields=1)
		fi	
		exit 0
	fi
done

echo "$1 non trouvé"

exit 1