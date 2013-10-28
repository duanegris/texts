#! /bin/bash


cat ventes.dat | cut -d" " -f1-2 | sort -k 2
