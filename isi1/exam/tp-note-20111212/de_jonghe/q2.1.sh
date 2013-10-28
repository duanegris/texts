2.1)

if [ $# -gt 2 ]
then 
	if [ $1 != "sel "]||[ $2 != "som " ]
	then
	echo -n " usage: ./<operation> <valeur>"
	fi	
exit 1
fi


2.2) # sel; $c numéro de colonne
echo -n "donner la colonne que vous voulez voire affiché"
read c
sel=`cat ventes.dat | cut -d " " -f1,$c`
nb_ligne= `grep -f $sel`
echo -n "le nombre de ligne est $nb_ligne "

#et dans le script 


if [ $# -gt 2 ]
then 
	if [ $1 != "sel "]||[ $2 != "som " ]
	then
	echo -n " usage: ./<operation> <valeur>"
	else	
		echo -n "donner la colonne que vous voulez voire affiché"
		read c
		sel=`cat ventes.dat | cut -d " " -f1,$c`
		nb_ligne= `grep -f sel`		#donne le nbre de ligne du fichier sel
		echo -n "le nombre de ligne est $nb_ligne "
	fi	
exit 1
fi



2.3)	#la somme
res=0
echo -n "donner le mois"
read mois
ligne=`grep  $mois ventes.dat`			#donne la ligne où le mois donné apparait
for i in 6					#6 colonnes
do
	val=`cat ligne | cut -d " " -f $i`		#j'envoie la ligne et je ne garde que la ième colonne ce qui donne la valeur
	res=$((res+val))
done
echo -n "la sommes des valeurs pour le mois $mois est $res"


#et dans le script



if [ $# -gt 2 ]
then 
	if [ $1 != "sel "]||[ $2 != "som " ]
	then
	echo -n " usage: ./<operation> <valeur>"
	else	
		echo -n "donner la colonne que vous voulez voire affiché"
		read c
		sel=`cat ventes.dat | cut -d " " -f1,$c`
		nb_ligne= `grep -f sel`		#donne le nbre de ligne du fichier sel
		echo -n "le nombre de ligne est $nb_ligne "
		res=0
		echo -n "donner le mois"
		read mois
		ligne=`grep  $mois ventes.dat`			#donne la ligne où le mois donné apparait
		for i in 6					#6 colonnes
		do
			val=`cat ligne | cut -d " " -f $i`		#j'envoie la ligne et je ne garde que la ième colonne ce qui donne la valeur
			res=$((res+val))
		done
		echo -n "la sommes des valeurs pour le mois $mois est $res"
		
	fi	
exit 1
fi










