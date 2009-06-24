#!/usr/bin/perl -w

use SOAP::Lite ;

### Acc�s au service de recherche Google d�crit par WSDL


print "Recherche Google :";
$query = <STDIN>;
chomp($query);

$key = "RegvGPFQFHKAht9PBeGYj2b/UHz7QA3d"; # ma cl� a moi

my $result = SOAP::Lite
   -> service('http://api.google.com/GoogleSearch.wsdl ')
   -> doSpellingSuggestion(
		$key, 		# key , google se prot�ge
            $query, 		# search query
            );

if (defined($result)) {
   print "Suggestion : ",$result, "\n";
}
else {
   print "Pas de suggestion suppl�mentaire.\n";
}

