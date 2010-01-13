<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>PHP processing of form</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body> 

<h1>Traitement PHP des valeurs passées par GET</h1>

<?php

$valeur_a 	= $_GET['a'];
$valeur_b 	= $_GET['b'];

echo "J'ai récupéré a=$valeur_a et b=$valeur_b";

?>


</body>
</html>
