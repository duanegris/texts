

<?php

	// exemple PHP qui
	// - regle le fuseau horaire sur France
	// - indique que les messages (noms de mois, etc) seront affichés en francais
	// les 2 dernières lignes montrent une conversion d'une date donnée en format US en date francaise textuelle

	$ok = date_default_timezone_set('Europe/Paris');
	setlocale(LC_TIME, "fr_FR");
	$d="2006-10-28";
	$formdate = strftime("%e %B %Y", strtotime($d));
	echo "$formdate\n";

	$french = "10-12-2011";
	$us = strftime("%m-%e-%Y", strtotime($french));
	echo "$us\n";


	// afficher la date courante à la norme US
	$d_us= date("Y-m-d");
	$d_fr= date("d-m-Y");
	echo "Nous sommes le (annee-mois-jour) :  $d_us\n";
	echo "Nous sommes le (jour-mois-annee) :  $d_fr\n";

	
?>
