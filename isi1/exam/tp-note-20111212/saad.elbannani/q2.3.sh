#!/bin/sh

nbarg=$*
op=$1
val=$2
fichier="ventes.dat"


grep $val $fichier | cut -c6- | wc -w




