#!/bin/bash

arg1=$1
arg2=$2

if [ $# -ne 2 ] 
then
	echo "Usage $0 $1 $2 "
	exit 1  
    
    else if [ -d $1 ]
	  then
             if [ -f $2 ]
	     then  
                 echo "->la condition non satisfaite."
                 exit 2
             else 
                 echo "success"
                 mkdir $2		# [SG] non, vers fichier 
                 # [SG] chmod ??
                 echo "Bienvenue [`date`] [ `who | cut -f1 -d' ' | uniq `]" # [SG] vers fichier
                 exit 0
             fi
          else 
             echo "->la condition non satisfaite."
             exit 2
         fi
    fi
done

