#!/usr/bin/perl -w

use SOAP::Lite ;

### Accès au service de recherche Google décrit par WSDL


print "Recherche Google :";
$query = <STDIN>;
chomp($query);

$key = "RegvGPFQFHKAht9PBeGYj2b/UHz7QA3d"; # ma clé a moi

my $result = SOAP::Lite
   ##-> service('file:///home/genaud/Documents/cvs/course/sd/src/soap/wsdl/GoogleSearch.wsdl')
   -> service('http://api.google.com/GoogleSearch.wsdl')
   -> doGoogleSearch(
		$key, 		# key , google se protège
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

	print "Nombre estimé de résultats: ", $result->{estimatedTotalResultsCount},"\n";

}

