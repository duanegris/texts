# Pour tous les fichiers du répertoire courant
for fichier in `ls`
do
	# si le fichier est régulier ...
	if [ -f "$fichier" ]
	then
		prefixe=`echo $fichier|cut -d'.' -f1`
		ext=`echo $fichier|cut -d '.' -f2`
		
		# si le préfixe est composé d'un caractère est que l'extension est date ...
		if [ `echo "$prefixe"|wc -c` -eq 2 ] && [ $ext = "dat" ]
		then
			# on créer un répertoire de nom prefixe-date
			rep=$prefixe-`date +%d-%m-%Y`
			if [ ! -d $rep ] # on vérifie qu'il n'existe déjà pas
			then
				mkdir $rep
			fi
			# on copie le fichier dans ce nouveau répertoire
			cp $fichier $rep 
		fi
	fi
done