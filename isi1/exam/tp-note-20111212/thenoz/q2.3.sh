a=$#
if [ "$a" != "2" ]
	then echo -e " usage: ./q2.2.1 <opertion> <valeur> \n 1"
	else
	grep $2 ventes.dat
fi
