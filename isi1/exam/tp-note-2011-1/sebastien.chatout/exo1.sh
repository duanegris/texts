# CHATOUT

#! /bin/bash

if [ $# -ne 2 ]
   then echo "exo1.sh arg1 arg2"; # $0 est mieux
fi

if [ -d $1 ] 
    then echo $1 " est un repertoire"
    else (echo $1 "n'est pas un repertoiredzns le repertoire courant";
    exit 2;)
fi

if [ -f $1 ]   # erreur : $2
    then (echo $2 "est un fichier";
         exit 2;)
fi

if [ -d $2 ]
     then (echo $2 "est un repertoire";
	    exit 2;)
fi

if [ -s $2 ]
     then (echo $2 "est un fichier spécial"
           exit 2;)
fi

echo "success";
exit 0;

# les ; inutiles