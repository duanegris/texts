#!/bin/sh

# S. Genaud, 19 nov 2009

OUTPUT_ALLFILES="touslesfichiers"

choix=""
while [ "$choix" != "9" ]
do 

	cat << EOT
************************ Menu général ************************
<1> Afficher la date
<2> Afficher les noms de personnes loguées sur la même machine
<3> Affiche la liste des processus
<4> La place occupée par les fichiers du répertoire courant
<5> La taille et l'occupation du disque
<6> mettre dans un fichier "tousfichiers"le nom de tous les fichiers du répetoire
      courant récursivement
<9> Quitter
**************************************************************
EOT
	read choix

	if [ "$choix" != "9" ]
	then
		case $choix in
		1) echo "[ `date`]";;	# les crochets ne sont la que pour clarifier l'affichage
		2) echo "[ `who | cut -f1 -d' ' | uniq `]";;
		3) echo "[ `ps`]";;
		4) echo "[ `du -sh | cut -f1`]";;
		5) echo "[ `df -h `]";;
		6) echo "[ `find . -print | tee $OUTPUT_ALLFILES`]"
		   echo "-> resultat ecrit dans $OUTPUT_ALLFILES";;
		esac

	fi
done
