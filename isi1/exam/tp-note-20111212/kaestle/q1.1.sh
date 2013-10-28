#!/bin/bash

datafile="ventes.dat"

cat $datafile | cut -d' ' -f 1-2 | sort

