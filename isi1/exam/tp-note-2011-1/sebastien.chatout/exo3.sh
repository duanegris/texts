#!/bin/bash

nom="executable_"`date +%d`"_"`date +%m`"_"`date +%y`
echo $nom
mkdir /tmp/$nom
chmod g+rw /tmp/$nom

./exo3_aux.sh . /tmp/$nom 

#rmdir /tmp/$nom 