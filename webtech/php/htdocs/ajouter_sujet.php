<html>
<head>
<title> Ajouter sujet </title>
</head>
<body>

<?php
$host="localhost";
$username="root";
$password="root";
$db_name="forum";
$tbl_name="questions";

echo "HERE !!!!!!!<br/>\n\n";

mysql_close();

$link=mysql_connect("$host","$username","$password") or die ("Ne peut pas se connecter");
mysql_select_db("$db_name") or die ("Ne peut pas trouver la base de données");

$sujet=$_POST['sujet'];
$detail=$_POST['detail'];
$auteur=$_POST['auteur'];
$datetime=date("d/m/y h:i:s");

$sql="INSERT INTO $tbl_name(sujet,detail,auteur,datetime) VALUES('$sujet','$detail','$auteur','$datetime')";
$result=mysql_query($sql);

if($result){
echo "Succes";
echo "<a href=forum.php> Voir votre sujet </a>";
}
else {
echo "Erreur";
}
mysql_close();
?>



</body>
</html>
