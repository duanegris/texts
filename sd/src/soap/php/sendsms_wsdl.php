
<?php
/*
https://www.esendex.com/secure/messenger/soap/SendService.asmx
*/

$account="EX0071093";
$username="genaud@unistra.fr";
$password="NDX1737";
$recipient="0033686267449";  /* use intl telephone numbers ! */
$originator="0686267449"; /* any number to identify sender */
$body="Ceci est mon test d'envoi de SMS";
$type="Text"; /* can be : Text, Binary, SmartMessage, Unicode. Default is Text */
$validityperiod="0"; /* validity in hours. 0 means never expires */


$wsdl_url = "https://www.esendex.com/secure/messenger/soap/SendService.asmx?WSDL";

   $c = new SoapClient($wsdl_url, array("trace"=>1));

/* To analyze the WSDL, you can call the following useful functions */

   /*
   print_r( $c->__getFunctions());
   print_r( $c->__getTypes());
   */


/* The services expects to receive crendentials in the header 
   It waits for :
 
  <soap:Header>
    <MessengerHeader xmlns="com.esendex.ems.soapinterface">
      <Username>string</Username>
      <Password>string</Password>
      <Account>string</Account>
    </MessengerHeader>
  </soap:Header>
*/
   $header = new SOAPHeader("com.esendex.ems.soapinterface",
				"MessengerHeader", 
				array(
					"Username"=> $username,
					"Password"=> $password,
					"Account"=>$account)
				);
   $c -> __setSoapHeaders( $header );

/* Now call te function 'SendFullMessage' */
   try {
	$c->SendMessageFull(array(
					"originator" => $originator, 
					"recipient"=> $recipient,
					"body"=> $body,
					"type"=> $type,
					"validityperiod"=> $validityperiod,
				));
   }
   catch (SoapFault $fault) {
	die( $fault->faultstring);
   }

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
