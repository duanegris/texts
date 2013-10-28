#! /bin/bash

jj=`date +"%d"`
mm=`date +"%m"`
aa=`date +"%y"`

nom="/tmp/executable_$jj-$mm-$aa"

if [ -d $nom ]
then
	echo "Le repertoire $nom existe deja"
	exit 1
else
	mkdir $nom
	chmod g+rw $nom
	
	lgrep="`ls | grep .exe`" # A refaire avec find
	
	for i in $lgrep
	do
		cp "$i" "$nom/$i"
	done
fi
	

