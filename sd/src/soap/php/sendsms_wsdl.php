
<?php
/*
https://www.esendex.com/secure/messenger/soap/SendService.asmx
*/


$account="genaud@unistra.fr";
$pwd="NDX1737";
$msg="Ceci est mon test d'envoi de SMS";
$telnum="00330686267449";
$org="0686267449"; /* any number to identify sender */

$wsdl_url = "https://www.esendex.com/secure/messenger/soap/SendService.asmx?WSDL";
   
   $c = new Soapclient($wsdl_url);
   print_r( $c->__getTypes());
/*
   $SoapResponse = $c->SendFullMessage(array(	"Originator" => $org, 
								"Account" => $account, 
								"Password"=> $pwd,
								"Password"=> $pwd,
								"Password"=> $pwd,
								));
   $result = $SoapResponse -> GetLocationByIPResult;
   if (!isset($result->Error)) {
   	print("IP $query localisée aux coordonnées géographiques (lat,lon) : ".$result->Latitude ."°,". $result->Longitude."°\n");
	print("Degré de confiance de la réponse : ".$result->Certainty . "%\n");
   }
   else
 	print("Erreur : " . $result->Error->Desc . "\n");
*/



/****
Array
(
    [0] => struct SendMessage {
 string recipient;
 string body;
 MessageType type;
}
    [1] => string MessageType
    [2] => struct SendMessageResponse {
 string SendMessageResult;
}
    [3] => struct MessengerHeader {
 string Username;
 string Password;
 string Account;
}
    [4] => struct SendMessageFull {
 string originator;
 string recipient;
 string body;
 MessageType type;
 int validityperiod;
}
    [5] => struct SendMessageFullResponse {
 string SendMessageFullResult;
}
    [6] => struct SendMessageMultipleRecipients {
 ArrayOfString recipients;
 string body;
 MessageType type;
}
    [7] => struct ArrayOfString {
 string string;
}
    [8] => struct SendMessageMultipleRecipientsResponse {
 ArrayOfString SendMessageMultipleRecipientsResult;
}
    [9] => struct SendMessageMultipleRecipientsFull {
 string originator;
 ArrayOfString recipients;
 string body;
 MessageType type;
 int validityperiod;
}
    [10] => struct SendMessageMultipleRecipientsFullResponse {
 ArrayOfString SendMessageMultipleRecipientsFullResult;
}
    [11] => struct SendMessageBatch {
 ArrayOfMessagesubmission messages;
}
    [12] => struct ArrayOfMessagesubmission {
 messagesubmission messagesubmission;
}
    [13] => struct messagesubmission {
 string originator;
 string recipient;
 string body;
 MessageContentType type;
 int validityperiod;
}
    [14] => string MessageContentType
    [15] => struct SendMessageBatchResponse {
 ArrayOfString SendMessageBatchResult;
}
    [16] => struct GetMessageStatus {
 string id;
}
    [17] => struct GetMessageStatusResponse {
 MessageStatusCode GetMessageStatusResult;
}
    [18] => string MessageStatusCode
)
**/


?>
