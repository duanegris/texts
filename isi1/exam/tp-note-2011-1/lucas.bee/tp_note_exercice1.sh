# BEE
#!/bin/bash

if [ $# -ne 2 ]
then echo "usage $0 arg1 arg2"
exit 1
fi

if [ -d $1 ]
then 
    if [ -e $2 ]
    then echo "le deuxieme argument est un fichier/dossier"
    exit 2
    fi
    echo "success"
    exit 0
else echo "le premier argument n'est pas un dossier"
exit 2
fi
       
