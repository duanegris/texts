#!/bin/bash
#-------------------------------------------------------------------
# commande (simulee) d'effacement recursif de fichier ou repertoire
# nécessite: confirm.sh
#-------------------------------------------------------------------
#La difficulté principale est de n'effacer un repertoire que si l'utilisateur confirme
#la destruction de tous les fichiers et répertoires contenus à l'intérieur.
#Pour ceci, j'ai une variable 'efface', initialisée à 1, que je mets à 0 dès que j'ai
#une réponse 'non' de l'utilisateur.  Et 'efface' et retournée comme code de retour.

#A chaque nouveau répertoire, j'appelle le programme (donc récursivement) avec comme
#arguments les fichiers et répertoires contenus (`ls $i`).

#Il y a bcp de if then else imbriqués, mais il n'y a en fait que 2 cas : 
#celui d'un répertoire ou celui d'un fichier. Un test supplémentaire s'ajoute 
#pour le cas où un rép. est vide ( [ -n $var ] est vrai si var est une chaine 
#qui n'est pas vide).
#-------------------------------------------------------------------


CONFIRM="./confirm.sh"

efface=1
for i in $*
do
	if [ -d $i ]
	then	# cas d'un repertoire
		$CONFIRM $i
		if [ $? -eq 1 ]
		then  # on veut l'effacer
			if [ -n "`ls $i`" ] # vide ou pas ?
					        # ne pas omettre les "" ou il y aura une erreur de syntaxe si rep vide
			then
				$0 $i/*    # $0 = le programme (portable en cas de renommage du programme)
				if [ $? -eq 1 ]
				then
					echo "-> repertoire $i efface."
				else
					echo "-> repertoire $i conserve."
					efface=0
				fi
		 	else # repertoire vide 
				echo "-> repertoire $i efface."
			fi
		else # on ne veut pas effacer
			echo "-> repertoire $i conserve."
			efface=0
		fi
	else  # autre cas
		if [ -f $i ]  # fichier ?
		then
			$CONFIRM $i
			if [ $? -eq 1 ]
			then  # on veut l'effacer
				echo "-> fichier $i efface."
			else
				echo "-> fichier $i conserve."
				efface=0
			fi
		else # pas un fichier
			echo "-> $i: pas un fichier"
		fi
	fi
done
exit $efface
	
	

			
			
			
