#!/bin/sh


fichier="ventes.dat"

cut  $fichier -f1,2 -d' '| sort
