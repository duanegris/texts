#!/bin/sh

colo=$1

v=`cat ventes.dat | cut -d' ' -f$colo`

echo $v
