#!/bin/bash

if [ $# -ne 2 ]
then
    echo 'usage arg1 arg2'
    exit 1
fi

if [ ! -d $1 ]
then
    echo '<arg1> doit être un repertoire'
    exit 2
fi

if [ -e $2 ]
then
    echo '<arg2> ne doit pas être un fichier (ni fichier régulier, ni répertoire, ni fichier spécial).'
    exit 2
fi

echo 'Bonjour' `date -d now +%d` $USER > $2

exit 0
