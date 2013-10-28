# !/bin/sh

file=/home/tian/Bureau/TIAN/ventes.dat
echo "`$file | cut -f3 -d' '` "
echo "`$file | cut -f2 -d' ' | sort -g`"



