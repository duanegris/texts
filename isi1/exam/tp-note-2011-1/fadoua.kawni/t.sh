
lsrep="`ls $rep`"

for i in $lsrep
do
	tt=`ls -l $i | tr -s " " | cut -d" " -f5`
	t=`expr $t + $tt`
done

echo "La taille des fichiers dans le répertoire $rep est de $t octets"

