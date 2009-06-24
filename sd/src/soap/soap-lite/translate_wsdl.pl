#!/usr/bin/perl -w


# methode listée à http://xmethods.net/

#use SOAP::Lite;
use SOAP::Lite +trace => 'debug'; 


print "Traduire (anglais->francais) :";
$atraduire = <STDIN>;
chomp($atraduire);
  
$toto = SOAP::Lite 
        -> service('http://www.webservicex.com/TranslateService.asmx?WSDL')
	  -> Translate( ([LanguageMode => 'EnglishTOFrench', Text=>$atraduire]));

print "Traduction :",$toto,"\n"; 
