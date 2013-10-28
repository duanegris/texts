#! /bin/bash

var=`date +%d-%m-%Y`
find $HOME -name $1.dat
mkdir $1-$var
cp $1.dat $1-$var
