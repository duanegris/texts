
echo -n "supprimer $1 [o/n] ? "
read rep
case $rep in
	o|O) exit 1;;
	n|N) exit 2;;
esac
