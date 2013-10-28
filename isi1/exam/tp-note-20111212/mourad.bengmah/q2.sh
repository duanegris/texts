file=vente.dat

# Question 2.1
# Si le script n'a pas deux arguments, on quitte le programme
if [ $# -ne 2 ]
then
	echo "Usage: /q2.sh <operation> <valeur>"
	exit 1
fi

operation=$1
valeur=$2

# Si l'opération n'est pas conforme, on quitte le programme
if [ $operation != "sel" ] && [ $operation != "som" ]
then
	echo "Le premier argument doit être égal a 'sel' ou 'som'"
	exit 1
fi

# Question 2.2
if [ $operation = "sel" ]
then
	cat $file | cut -d ' ' -f $valeur
fi

# Question 2.3
if [ $operation = "som" ]
then
	# On selectionne les valeurs du mois
	ligne=`cat $file|grep $valeur|cut -d ' ' -f 2-`
	
	# On somme toutes ses valeurs
	somme=0
	for val in $ligne
	do
		somme=$(($somme + $val))
	done

	echo "$valeur -> total : $somme"
fi

