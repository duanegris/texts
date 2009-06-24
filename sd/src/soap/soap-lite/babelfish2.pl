#!/usr/bin/perl -w


# methode listée à http://xmethods.net/

use SOAP::Lite;
#use SOAP::Lite +trace => 'debug'; 


print "Traduire (anglais->francais) :";
$atraduire = <STDIN>;
chomp($atraduire);
  
$toto = SOAP::Lite 
        -> service('http://www.xmethods.net/sd/2001/BabelFishService.wsdl')
	  -> BabelFish('en_fr',$atraduire);

print "Traduction :",$toto,"\n"; 
