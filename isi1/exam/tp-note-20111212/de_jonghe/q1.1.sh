a=` cat ventes.dat | cut -d " " -f 1,2 `

cut -d " " -f 2 a | sort -n	#prend la colonne des valeurs et la trie
