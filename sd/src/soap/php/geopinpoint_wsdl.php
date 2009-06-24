

<?

$LicenseKey="WS1-LTC1-BOF1"; //!! valide du 7 nov au 21 nov 2006
/*$Address = "

	$SoapResponse = $soapClient->getBestMatch(array(
		"Address" => $Address,
		"City" => $City,
		"State" => $State,
		"PostalCode" => $Zip,
		"LicenseKey" => $LicenseKey));

	

	//Store result node in a more convenient variable

	$ResultNode = $SoapResponse->GetBestMatchResult;
	echo "=$ResultNode->Latittude";
	echo "=$ResultNode->Longitude";


*/
$query="130.79.192.160";
$wsdl_url = "http://trial.serviceobjects.com/gpp/GeoPinPoint.asmx?WSDL";
   
   $c = new Soapclient($wsdl_url);
   //print_r( $c->__getTypes());
   $SoapResponse = $c->GetLocationByIP(array("IPAddress" => $query, "LicenseKey" => $LicenseKey));
   $result = $SoapResponse -> GetLocationByIPResult;
   if (!isset($result->Error)) {
   	print("IP $query localisée aux coordonnées géographiques (lat,lon) : ".$result->Latitude ."°,". $result->Longitude."°\n");
	print("Degré de confiance de la réponse : ".$result->Certainty . "%\n");
   }
   else
 	print("Erreur : " . $result->Error->Desc . "\n");
?>
