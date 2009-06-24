<?
/*
 * client PHP (classe Soap native) pour methode Define (Stephane Genaud, 12 Nov 2006)
 *
 */

	$wsdl_url = "http://services.aonaware.com/DictService/DictService.asmx?WSDL";
   	$SoapClient = new Soapclient($wsdl_url);
   
	$SoapResponse = $SoapClient->Define(array("word" => "Moon"));

// debug. Uncomment following line to see whole result structure:
//var_dump($Result);


	$Result = $SoapResponse->DefineResult;
	echo "-> Resultat pour $Result->Word :\n";


      echo "-> Nom du dictionnaire donnant la 1ere def :\n";
      $DicoDef1 = $Result->Definitions->Definition[0]->Dictionary->Name;
      print("+-----------------------------------------------------------------+\n");
      print("| $DicoDef1\n");
      print("+-----------------------------------------------------------------+\n");

	print("\nEnsemble des définitions:\n");
      print("+-----------------------------------------------------------------+\n");
	foreach ($Result->Definitions as $c => $partie_def) {  // 1 seul element dans Definitions 
            echo "\n\n_____________($c)____________________\n";
		foreach ($partie_def as $defnumber => $val) {
			print("Def $defnumber \n======\n");
				print("--: $val->Word --> $val->WordDefinition\n");
		}
	}	

/*
 * _________ structure object $Result ______________________*

i.e. var_dump($Result) = 


object(stdClass)#3 (2) {
  ["Word"]=>
  string(4) "Moon"
  ["Definitions"]=>
  object(stdClass)#4 (1) {
    ["Definition"]=>
    array(7) {
      [0]=>
      object(stdClass)#5 (3) {
        ["Word"]=>
        string(4) "Moon"
        ["Dictionary"]=>
        object(stdClass)#6 (2) {
          ["Id"]=>
          string(5) "gcide"
          ["Name"]=>
          string(60) "The Collaborative International Dictionary of English v.0.48"
        }
        ["WordDefinition"]=> istring(485) " blah blah... "
	  ....
*/
?>
