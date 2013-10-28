#!/bin/bash
 
$jj=`cut -d "" -f2 date`
$mm=`cut -d "" -f3 date`
$aa=`cut -d "" -f4 date`


cd /tmp
`mkdir executable_$jj_$mm_$aa`;
`chmod +wr ugo executable_$jj_$mm_$aa`;
cd ..
if [-d $i]
then cd ../$i/$0;
else if [-x $i]
then `cp $i copy_$i`;
`mv copy_$i ../tmp/executable_jj_mm_aa`;
fi