fich_selec=$(find ./ -name "*.dat" | cut -d"/" -f2- | grep "^[0-9,a-z].dat")
for i in `echo $fich_selec`
do
	if [ -f $i ]
	then
		date_fich=$(date +%d-%m-%y)	
		nom_fich=${i%.*}
		mkdir "./$nom_fich-$date_fich"
		cp $i "./$nom_fich-$date_fich/$i"
	fi
done
