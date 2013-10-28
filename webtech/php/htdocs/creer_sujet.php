<html>
<head>
<title> Créer sujet </title>
</head>
<body>



<form name="formulaire1" method="post" action="ajouter_sujet.php">

<table>
<tr>
<td> <h2> Créer un nouveau sujet </h2> </td>
</tr>
<tr>
<td> Sujet </td>
<td> : </td>
<td> <input name="sujet" type="text"> </td>
</tr>
<tr>
<td> Détail </td>
<td> : </td>
<td> <textarea name="detail" cols="50" rows="3"> </textarea> </td>
</tr>
<tr>
<td> Auteur </td>
<td> : </td>
<td> <input name="auteur" type="text"> </td>
</tr>
<tr>
<td>&nbsp;</td>
<td>&nbsp;</td>
<td> <input type="submit" name="envoi" value="Envoi"> 
     <input type="reset" name="reset" value="Effacer"> 
</td>
</tr>
</table>

</form>
</body>
</head>