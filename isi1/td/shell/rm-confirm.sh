
for i in $*
do
	if [ -f $i ]
	then
		echo "supprimer $i [o/n] ?"
		read rep
		case $rep in
			o|O) echo "-> $i supprimé" ;;
			n|N) echo "-> ignoré" ;;
		esac
	elif [ -d $i ]
	then
		echo "$i est un repertoire. ignoré"
	fi
done
