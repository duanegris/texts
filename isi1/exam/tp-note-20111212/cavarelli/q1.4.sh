#! /bin/bash

for i in `seq 6`
do
	echo "`cat ventes.dat | cut -d " " -f$i`" > $i.dat
	chmod -w $i.dat
done 

rm -f 1.dat

