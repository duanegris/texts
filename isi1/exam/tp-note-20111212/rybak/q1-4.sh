for i in `seq 5`
do
	pos=$(expr $i + 1)
	data_mois=$(cat "ventes.dat" | cut -d" " -f1,$pos)	
	echo "$data_mois" > "$i.dat"
	chmod a-w $i.dat
done
