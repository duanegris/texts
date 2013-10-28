#! /bin/bash

if [ $# -ne 2 ]
   then echo "exo2.sh arg1 arg2";
fi

if [ -d $1 ] 
    then echo $1 " est un repertoire"
    else (echo $1 "n'est pas un repertoiredzns le repertoire courant";
    exit 2;)
fi

if [ -f $1 ]
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
echo "#!/bin/bash   # [SG] syntaxe , chaine non terminée, envoyé vers $2
echo \"Bienvenue \" `date +%d/%m/%Y`  `users| cut -d' ' -f1`" > $2;
chmod ugo+x $2

exit 0;