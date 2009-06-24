#!/usr/bin/perl -w
#
# Exemple de base pour accéder à un service dont on
# connait l'interface. Il retourne une seule chaine
# d'où l'utilisation de la valeur "->result" de l'objet
# SOAP::Lite (utiliser ->paramsout(); quand plusieurs
# sont retournées).
#
use SOAP::Lite +trace => 'debug';
#use SOAP::Lite;


my $soap = SOAP::Lite
    -> uri('http://www.soaplite.com/Temperatures')
    -> proxy('http://services.soaplite.com/temper.cgi');

# traduit des degrees celsius en fahrentheit
print $soap-> c2f(20)
           -> result, "\n";


