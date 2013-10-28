#!/bin/bash

dir_path=/tmp/executable_`date -d now +%d_%m_%y`

echo `du $dir_path --apparent-size` | cut -f1 -d" "
