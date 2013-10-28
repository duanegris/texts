#!/bin/sh

resultat=`find . -name "?.dat" | cut -c 3-`
vardate=`date "+%Y%m%d"`
res=`find . -name "?.dat" | cut -c 3`
mkdir "$res-$vardate"
cp $resultat ./$res-$vardate
