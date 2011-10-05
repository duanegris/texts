

<?php
// Example of PHP SOAP code to access an IP geolocation service.
// Sample code, S. Genaud dec 2007

// free license obtained from fraudlab
// 90 credits / month, ask one at :
// http://www.fraudlabs.com/freelicense.aspx?PackageID=1
$LicenseKey="02-WVGM-1BA6"; 


$query="130.79.192.160";
$wsdl_url = "http://ws.fraudlabs.com/ip2locationwebservice.asmx?wsdl";
   
   $c = new Soapclient($wsdl_url);
   print_r( $c->__getTypes());
   $SoapResponse = $c->IP2Location(array("IP" => $query, "LICENSE" => $LicenseKey));
   $result = $SoapResponse -> IP2LocationResult;
   print_r($result);
   if (!isset($result->Message)) {
   	print("IP $query localisée en (lat,lon): ".$result->LATITUDE ."°,". $result->LONGITUDE."°\n");
	print("Pays  : ".$result->COUNTRYNAME . "\n");
	print("Ville : ".$result->CITY . "\n");
   }
   else
 	print("Erreur : " . $result->Message . "\n");
?>
