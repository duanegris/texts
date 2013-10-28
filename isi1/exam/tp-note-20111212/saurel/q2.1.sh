if [ $# != 2 ]; then
	printf 'Vous devez entrer 2 arguments\n'
elif [ $1 != 'sel' ] && [ $1 != 'som' ]; then
	printf 'Premier argument incorrect (sel ou som)\n'
else
	printf 'Correct\n'
	cut -c -4 ventes.dat #colonne 1
	
fi
