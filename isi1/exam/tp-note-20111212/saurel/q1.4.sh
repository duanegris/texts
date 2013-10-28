colonne1='cut -c -4 ventes.dat'
"$colonne1">>1.dat  #ne fonctionne pas...
chmod -w 1.dat

colonne2='cut -c 6-7 ventes.dat'
"$colonne2">>2.dat  #ne fonctionne pas...
chmod -w 2.dat

colonne3='cut -c 9-10 ventes.dat'
"$colonne3">>3.dat  #ne fonctionne pas...
chmod -w 3.dat

colonne4='cut -c 12-13 ventes.dat'
"$colonne4">>4.dat  #ne fonctionne pas...
chmod -w 4.dat

colonne5='cut -c 15-16 ventes.dat'
"$colonne5">>5.dat  #ne fonctionne pas...
chmod -w 5.dat

colonne6='cut -c 18-19 ventes.dat'
"$colonne6">>6.dat  #ne fonctionne pas...
chmod -w 6.dat
