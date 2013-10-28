#!/bin/bash

# if [ $# -ne 1 ]
# then echo "usage $0 arg1"
# exit 1
# fi

date=`date +"%d_%m_%y"`
repertoir="executable_$date"
cd /tmp
	if [ -d $respertoir ]
	then
		echo "le répertoire existe déja"
	else
		mkdir $repertoir
		chmod g+rw $repertoir
	fi
	
#cd /$1

	fichier=`ls | grep *.exe`
	for i in $fichier
	do
		mv $i /tmp/$repertoir
	done
	# pas eu le temps de finir
	# for i in `ls`
	# do
		# if [ -d $i ]
		# then $0 $i
		# exit 1
		# fi
	# done

			
		