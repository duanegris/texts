#!/usr/bin/perl -w

#use SOAP::Lite;
use SOAP::Lite +trace => 'debug'; 


if (!defined(@ARGV)) {
	  print "Usage : whois.pl <domainname>\n";
}
else {
  #!perl -w

    use SOAP::Lite;

    # 4s4c (aka Simon's SOAP Server Services For COM) http://www.4s4c.com/

    my $p = SOAP::Lite 
	 -> uri('http://www.pocketsoap.com/whois')
	 -> proxy('http://soap.4s4c.com/whois/soap.asp')
	 -> whois(SOAP::Data->name('name' => $ARGV[0]))
	 -> result;

    unless ($p->fault) {
  	   print $p->result;
    }
    else {
		print join ", ", 
		$p->faultcode,$p->faultstring;
    }
    
}

