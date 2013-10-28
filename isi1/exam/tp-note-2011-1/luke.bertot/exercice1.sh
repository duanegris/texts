#!/bin/bash

if ([ $# != 2 ]) then
    echo "usage $0 arg1 arg2";
    exit 1;
fi

if ([ -d $1 ]) then	# parenthèses inutiles
    if ([ -e $2 ]) then
	echo "$2 existe dans le répertoire courrant";
	exit 2;
    else
	echo "succes";	# point virgule iutile
    fi
else
	echo "$1 n'est pas un répertoire existant";
	exit 2;
fi

exit 0;