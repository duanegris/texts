#!/usr/bin/perl -w

use SOAP::Lite ;

### Acc�s au service de recherche Google d�crit par WSDL


print "Recherche Google :";
$query = <STDIN>;
chomp($query);

$key = "RegvGPFQFHKAht9PBeGYj2b/UHz7QA3d"; # ma cl� a moi

my $result = SOAP::Lite
   ##-> service('file:///home/genaud/Documents/cvs/course/sd/src/soap/wsdl/GoogleSearch.wsdl')
   -> service('http://api.google.com/GoogleSearch.wsdl')
   -> doGoogleSearch(
		$key, 		# key , google se prot�ge
            $query, 		# search query
		0, 			# start results
		10, 			# max results (MAX 10)
		"false", 		# filter: boolean
		"", 			# restrict (string)
		"false", 		# safe search : site X ?
		"lang_fr", 		# lr
		"latin1", 		# ie
		"latin1" 		# oe
            );


if (defined($result->{resultElements}) ) {

	print "Nombre estim� de r�sultats: ", $result->{estimatedTotalResultsCount},"\n";

}

