<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>Basic code to connect to a MySQL DB</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body> 

<h1>Connect and list table values</h1>


<?php
$link = mysql_connect('localhost', 'root', 'root');
mysql_select_db("magasin") or die("pas possible de trouver la basei 'magasin'");

$result = mysql_query("SELECT * FROM produits");
$nblignes = mysql_numrows($result);

if ($nblignes > 0) {

	  echo "<table border='1'>
		    <tr>
		    <td>ref</td><td>Marque<td>Désignation</td><td>Prix</td></tr>\n";
	  for ($i=0;$i<$nblignes;$i=$i+1) {
		    $ref 	=  mysql_result($result,$i,"ref");
		    $marque 	=  mysql_result($result,$i,"marque");
		    $prod_nom 	=  mysql_result($result,$i,"modele");
		    $prod_prix =  mysql_result($result,$i,"prix");
		    echo "<tr><td>$ref</td><td>$marque</td><td>$prod_nom</td><td>$prod_prix</td></tr>\n";
	  }
	  echo "</table>";
}
else {

	echo "Pas de produit dans la table.<br/>";

}
mysql_close();

?>



</body>
</html>
