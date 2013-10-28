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
		taille=$(du -b $1 | cut --fields=1)

		if [ -x $1 ]
		then
			echo "$1 trouvé"
		else
			echo "$1 non exécutable"
		fi
		
		if [ $# -eq 3 ]; then
			echo $1 $taille
		fi

		$(find ~ -size ${taille}c 2> /dev/null > $3 )

		echo "Nombre de fichiers trouvés :" $(wc -l $3 | cut -d' ' --fields=1)
		cat $3

		for i in $(cat $3)
		do
			if [ -x $i ]
			then
				./$i
			fi
		done

		exit 0
	fi
done

echo "$1 non trouvé"

exit 1