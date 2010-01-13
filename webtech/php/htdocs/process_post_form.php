<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

	<title>PHP processing of form</title>
      <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
</head>

<body> 

<h1>Traitement PHP des valeurs passées par POST</h1>

<?php

$text1 	= $_POST['text1'];
$textarea1 	= $_POST['textarea1'];
$radio1 	= $_POST['radio1'];
$checkbox1 	= $_POST['checkbox1'];
$select1 	= $_POST['select1'];


echo "text1=$text1 <br/>";
echo "textarea1=$textarea1 <br/>";
echo "radio1=$radio1  <br/>";
echo "checkbox1=$checkbox1 <br/>";
echo "select1=$select1 <br/>";



?>


</body>
</html>
