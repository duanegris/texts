# !/bin/sh
# S. Genaud 19/nov/2009

motif=$1
motiflen=`echo "$motif" | wc -c` #nombre de caractères du motif (attention +1 pour terminal)
cmplen=$(($motiflen-2))          # sur quelle longeur comparer. -1 pour le 1er car, -1 terminal
delim=`echo $motif | cut -c1`  #le premier car du motif sert de delimiteur de jeton


shift	   #decalage des parametres positionels pour ne garder que les fichiers
sellignes="`grep $motif $*`"


tok="$1"qquechose  # init pour que ce ne soit pas le motif

cpt=1
occ=0
while [ "$tok" != "" ] 
do
	tok=`echo $sellignes | cut -f$cpt -d$delim`
	toksub=`echo $tok | cut -c1-$cmplen`
      if [ "$delim$toksub" = "$motif" ]
	then
		occ=$(( $occ+1 )) 
	fi
	cpt=$((cpt+1))
done 
echo "$occ occurences de '$motif'"
