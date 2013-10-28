#!/bin/sh

v=`cat ventes.dat | cut -d' ' -f1-2`

echo $v 



w=`cat ventes.dat | cut -d' ' -f1-2 | sort`

echo $w
