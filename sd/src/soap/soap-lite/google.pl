#!/usr/bin/perl -w

use SOAP::Lite;
# use SOAP::Lite +trace => 'debug';


### Accès au service de recherche Google décrit par WSDL


print "Recherche Google :";
$query = <STDIN>;
chomp($query);

$key = "RegvGPFQFHKAht9PBeGYj2b/UHz7QA3d"; # ma clé a moi

my $result = SOAP::Lite
   -> service('http://api.google.com/GoogleSearch.wsdl ')
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


if( defined($result->{resultElements}) ) {

	for my $i ( 0 .. $#{ $result->{resultElements} } ) {

        print "[",$i,"] ",$result->{resultElements}->[$i]->{title},"\n    ",
        $result->{resultElements}->[$i]->{URL},"\n\n";

	  ##----ou encore -------------
#            foreach my $element (@{$result->{'resultElements'}}) {
#	            foreach my $key (keys(%$element)) {
#		         print $key, ': ', $element->{$key}, "\n";
#		  }
#	  }

     }
}

