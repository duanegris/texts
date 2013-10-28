#!/bin/sh

reper=`find`

comp=1

fich=echo $reper|cut -d' ' -f1

while [ -s fich ]

do

name=echo $fich|cut -d'.' -f1

las=echo $fich|cut -d'.' -f2

  if [ -f fich ] then 
          if [ !(echo $name|cut -c2) ] then 
                  if [ las = "dat" ] then
                      jour=`date|cut -d',' -f1`
                      rep=`mkdir < $name-$jour>`
                      cp $fich $rep
                   
comp=$(($comp+1))

fich=echo $reper|cut -d' ' -f$comp

done
