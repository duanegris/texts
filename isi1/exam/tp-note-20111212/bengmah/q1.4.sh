file=vente.dat

# Données
# on protègle les [ ] car ce sont des caractères spéciaux
nb_col=$((`cat $file|grep "\[an\]"|wc -w` + 1))
col_lu=2

while [ $col_lu -ne $nb_col ]
do
	# on créer un fichier selon le numéro de la colonne
	f=$col_lu.dat
	
	# on redirige le résultat vers un fichier .dat
	cat $file | cut -d ' ' -f $col_lu > $f
	
	# on enlève les droits d'écritures du fichier
	chmod -w $f 
	
	# on incrémente notre boucle
	col_lu=$(($col_lu + 1))
done
