echo coucou
[ $# -lt 2 -o $# -gt 3 ] && echo "s1.sh fichier dossier" && exit 1
[ ! -d $2 ] && echo "$2 n'est pas un dossier" && exit 1

estTrouve=`ls $2/$1 2>/dev/null | wc -l`



if [ $estTrouve -ge 1 -a -f $2/$1 ] ; then

	if [ $# -eq 3 ] ; then
		ls -l $2/$1 | awk ' { print $8 "   " $5; }  '	
		taille=`ls -l $2/$1 | awk ' { print $5; }  '`

		find . -perm /u+x -size $taille > $3  2>/dev/null
		echo "nb fichier : "
		wc -l $3 | cut -d" " -f1
		echo "contenu : "
		cat $3

	fi
	
	exit 0
else
	exit 1
fi 
