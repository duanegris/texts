<?php

$client = new SoapClient(NULL,
        array(
        "location" => "http://64.124.140.30:9090/soap",
        "uri"      => "urn:xmethods-delayed-quotes",
        "style"    => SOAP_RPC,
        "use"      => SOAP_ENCODED
           ));

print($client->__Soapcall(
        /* SOAP Method Name */
        "getQuote",
        /* Parameters */
        array(
            new SoapParam(
                /* Parameter Value */
                "ibm",
                /* Parameter Name */
                "symbol"
        )),
        /* Options */
        array(
            /* SOAP Method Namespace */
            "uri" => "urn:xmethods-delayed-quotes",
            /* SOAPAction HTTP Header for SOAP Method */
            "soapaction" => "urn:xmethods-delayed-quotes#getQuote"
        )). "\n");
?> 
