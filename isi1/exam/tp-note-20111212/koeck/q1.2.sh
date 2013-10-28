#!/bin/bash

echo "Entrez une nouvelle ligne de données"

read donnees

echo -n "$donnees" > tmp
echo "" > tmp2

cat ventes.dat tmp2 tmp > tmp3
mv tmp3 ventes.dat
rm tmp
rm tmp2