#!/bin/sh

[ $# -ne 2 ] && echo "s1.sh fichier dossier" && exit 1
[ ! -d $2 ] && echo "$2 n'est pas un dossier" && exit 1

estTrouve=`ls $2/$1 2>/dev/null | wc -l`

[ $estTrouve -ge 1 -a -f $2/$1  ] && exit || exit 1

#@@gb pas verification executabilité