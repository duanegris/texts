#!/usr/bin/perl -w


# methode listée à http://xmethods.net/

use SOAP::Lite;
# decommenter la ligne usivante pour voir les messages XML
# use SOAP::Lite +trace => 'debug'; 


print "Traduire (anglais->francais) :";
$atraduire = <STDIN>;
chomp($atraduire);
  
$toto = SOAP::Lite -> uri('urn:xmethodsBabelFish') -> proxy('http://services.xmethods.net:80/perl/soaplite.cgi') -> BabelFish('en_fr',$atraduire);

unless ($toto->fault) {
    print "Traduction :",$toto->result,"\n"; 
}				    
else {
    print join ", ",
    $toto->faultcode,$toto->faultstring;
}
