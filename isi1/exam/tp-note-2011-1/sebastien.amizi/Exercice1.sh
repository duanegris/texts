# AZIMI

#!/bin/bash

if [ $# -ne 2 ]; then
    echo "usage $0 arg1 arg2"  # exit 1
else
    if [ -d $1 -a ! -f $2 ]; then
        echo "Succès!"  # exit 2
    else
        echo "Echec!"   # exit 0
    fi
fi
