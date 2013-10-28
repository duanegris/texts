#!/bin/bash

rep="/tmp/executable_`date +%d`_`date +%m`_`date +%y`"
mkdir $rep -p
chmod u+x $0	# [SG] non, le repertoire !
[ -f ./*.exe ] && cp ./*.exe $rep/ # [SG] ne descend pas dans l'arbo
