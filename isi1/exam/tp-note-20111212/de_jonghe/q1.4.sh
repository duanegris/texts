

i=2 #on commence à la 2ème colonne
while [ $i -ne 6 ]	#6 colonnes
do
	icolonne=`cut -d " " -f $i ventes.dat`
	cp $icolonne $i.dat
	i++
	chmod -w $i.dat
done
