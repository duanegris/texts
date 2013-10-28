#!/bin/bash

for (( i=1; i < 7; i++ )); do
cut -d' ' -f $i ventes.dat > $i.dat
chmod 444 $i.dat;
done