#!/bin/bash

#Question 1.1
#LIANG Yaohua
#sh q11.sh ventes.dat


cat $1 | grep [a-z] | cut -d ' ' -f1,2 | sort -n