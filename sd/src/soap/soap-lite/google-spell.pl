#!/usr/bin/perl -w

use SOAP::Lite ;

### Accès au service de recherche Google décrit par WSDL


print "Recherche Google :";
$query = <STDIN>;
chomp($query);

$key = "RegvGPFQFHKAht9PBeGYj2b/UHz7QA3d"; # ma clé a moi

my $result = SOAP::Lite
   -> service('http://api.google.com/GoogleSearch.wsdl ')
   -> doSpellingSuggestion(
		$key, 		# key , google se protège
            $query, 		# search query
            );

if (defined($result)) {
   print "Suggestion : ",$result, "\n";
}
else {
   print "Pas de suggestion supplémentaire.\n";
}

