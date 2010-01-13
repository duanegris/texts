<html>

<head>
</head>

<body>


<table border="1">

<?php

	for ($d=1 ; $d<=30; $d=$d+1 ) 
      {
		echo "<tr>";
		for ($c=1 ; $c<=30; $c=$c+1 ) 
      	{
		  $v = ($d-1) * 30 + $c;
		  echo " <td> $v  </td>";
		}
		echo "</tr>";
	}


?>

</table>


</body>
</html>
