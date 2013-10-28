#!/bin/bash

dir_path=/tmp/executable_`date -d now +%d_%m_%y`
if [ ! -d $dir_path ]
then
    mkdir $dir_path
    chmod g+x $dir_path
fi
find -name '*.exe' -execdir cp {} $dir_path \;

exit 0
