file=vente.dat

cat $file | cut -d ' ' -f-2 | sort --key=2