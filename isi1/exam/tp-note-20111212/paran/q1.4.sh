#!/bin/bash

COL=`grep janv ventes.dat | wc -w`

i=1

while [ $i -le $COL ]; do
	cat ventes.dat | cut -d " " -f$i > $i.dat
	chmod a-w $i.dat
	i=$(($i+1))
done
