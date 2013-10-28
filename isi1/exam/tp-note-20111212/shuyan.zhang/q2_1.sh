#!/bin/sh

choir=$1

chose=$2  

SEL="./sel.sh"

SOM="./som.sh"

case $choir in 

      sel)  $SEL chose
      
      som)  $SOM chose
  
      *) echo "errur"
esac
