#!/bin/sh

date=`date | tr -s " " | cut -d" " -f1,2,3`
echo "Bienvenue $date $USER"

jj=`date +%d`
mm=`date +%m`
aa=`date +%y`

un="executable_$jj"
deux="_$mm"
trois="_$aa"
mkdir /tmp/$un$deux$trois
chmod g+rw /tmp/$un$deux$trois

#VOIR recursive.sh





