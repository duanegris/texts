#!/bin/bash

file=$1.$2


if [ "$2" == "dat" ]
then
mkdir $1-`date +%d-%m-20%y`
cp $1.$2 $1-`date +%d-%m-20%y`