  <?php
$client = new
    SoapClient(
        "http://services.xmethods.net/soap/urn:xmethods-delayed-quotes.wsdl"
    );

print($client->getQuote("ibm"));
?> 
