#!/bin/bash

place=`pwd`

datedujour=`date +"%d_%m_%y"`

mkdir /tmp/executable_$datedujour

chmod g+x+r+w /tmp/executable_$datedujour

`$place/recherche_exe.sh $place`



