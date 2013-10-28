
for i in `ls`
do
if [ -f $i ]  
then
	n=`echo $i | cut -d "." -f1 `	#on garde le préfixe
	l=$(( $(echo $n | wc -c) -1 ))	#on regarde sa longueur
	if [ l -eq 1 ]
	then
		a =`date -%d-%m-%Y`
		mkdir n.a
		cp $i n.a
	fi
fi
done

	
	
