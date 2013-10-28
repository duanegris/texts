package SeriesSource::betaseries;

use strict;
use warnings;
use 5.010;

use Digest::MD5 qw(md5 md5_hex md5_base64);
use XML::LibXML;
use URI::Escape;

my $baseurl = "http://api.betaseries.com/";
my $serieurl = "https://www.betaseries.com/serie/";
my $key;

my $username;
my $password;

my $token;

sub init {
    my ($this, $config) = @_;

    $key = $config->{key};
    $username = $config->{username};
    $password = md5_hex($config->{password});
}

sub infos {
}

sub connect {
    my $parser = XML::LibXML->new;
    my $xml = $parser->load_xml(location => $baseurl."members/auth.xml?key=$key&login=$username&password=$password");

    my $root = $xml->documentElement();
    my ($node_token) = $root->getElementsByTagName("token");

    $token = $node_token->textContent();
}

sub disconnect {
    return if !defined $token;

    my $parser = XML::LibXML->new;
    $parser->load_xml(location => $baseurl."members/destroy.xml?key=$key");

    undef $token;
}

sub search {
    my ($this, $search_term) = @_;

    my $parser = XML::LibXML->new;
    my $xml = $parser->load_xml(location => $baseurl."shows/search.xml?title=".uri_escape($search_term)."&key=$key");
    my $root = $xml->documentElement();

    my @nodes = $root->getElementsByTagName("show");

    my @series;
    for my $node (@nodes) {
        my ($title) = $node->getChildrenByTagName("title");
        my ($url) = $node->getChildrenByTagName("url");

        push @series, { title => $title->textContent, url => $url->textContent };
    }
    return @series;
}

sub get_series {
    my ($this, $last_exec) = @_;

    $this->connect if !defined $token;

    my $parser = XML::LibXML->new;
    my $time = time();

    my $xml = $parser->load_xml(location => $baseurl."planning/member.xml?key=$key&view=unseen&token=$token");
    my $root = $xml->documentElement();

    my @nodes = $root->getElementsByTagName("item");

    my @series;
    for my $node (@nodes) {
        my ($show) = $node->getChildrenByTagName("show");
        my ($number) = $node->getChildrenByTagName("number");
        my ($date) = $node->getChildrenByTagName("date");

        if($date->textContent() < $time && $date->textContent() > $last_exec) {
            my ($season, $episode) = ($number->textContent =~ m/S([0-9]{2})E([0-9]{2})/);

            push @series, { name => $show->textContent(), season => $season, episode => $episode, date => $date->textContent };
        }
    }

    return @series;
}

sub display {
    my ($this, $search_term) = @_;

    my @series = $this->search($search_term);
    my $nb = scalar(@series);

    my $serie = 0;
    if ($nb > 1) {
        say scalar(@series), " séries trouvées\n";
        my $i = 0;
        foreach my $serie (@series) {
            say "$i) $serie->{title}";
            $i++;
        }
        print "Numéro de la série à afficher : ";
        $serie = <STDIN>;
        print "\n";
    }

    my $parser = XML::LibXML->new;
    my $xml = $parser->load_xml(location => $baseurl."shows/display/".$series[$serie]->{url}.".xml?key=$key");
    my $root = $xml->documentElement();

    my @nodes = $root->getElementsByTagName("show");
    my ($title) = $nodes[0]->getChildrenByTagName("title");
    my ($description) = $nodes[0]->getChildrenByTagName("description");

    say $title->textContent,"\n";
    say $description->textContent;

}

return 1;
