test $# -ne 2 && { echo "Usage : $0 <operation> <valeur>"; exit 1;}
if [ $1 == "sel" -o $1 == "som" ] # Test du premier paramètre
	then echo "Operation valable"
else
	echo "Operation non valable"
	exit 1
fi
operation=$1
valeur=$2
if [ $operation == "sel" ] 
	then
		data_mois=$(cat "ventes.dat" | cut -d" " -f$valeur)
		echo "$data_mois"
		nb_ligne=$(echo "$data_mois" | wc -l)
		echo "Nombre total ligne : $nb_ligne"
else # Operation = som
		ligne_mois=$(cat "ventes.dat" | grep "$valeur")
		mois=$(echo $ligne_mois | cut -d" " -f1)
		for i in `seq 5` # On considère 5 colonnes de valeurs par mois
		do
			pos=$(expr $i + 1) # Car on est décalé de 1
			total=$(expr $total + $(echo $ligne_mois | cut -d" " -f$pos))
		done
		echo "$mois -> total : $total"
fi
exit 0; # Tout s'est bien passé

# Exemples de test :
# ./q2-3.sh som dece
# ./q2-3.sh sel 4
