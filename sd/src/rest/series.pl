#!/usr/bin/perl
use strict;
use warnings;
use 5.010;

use Getopt::Long qw(:config bundling);

use SeriesSource::betaseries;

my $debug   = defined $ENV{DEBUG} ? $ENV{DEBUG} : 0;

my $key = "0e573a8029fd";
my $username = "Dev002";
my $password = "developer";

GetOptions(
    "help|h|?" => sub { help(); },
    "planning|p" => \my $list,
    "display|d=s" => \my $display,
    "search|s=s" => \my $search,
);

sub init {
    SeriesSource::betaseries->init({ key => $key, username => $username, password => $password});
}

sub help {
    say STDERR "Usage: $0 {-h|-l|-d|-s}\n";

    say "  -h\t--help\t\t\taffiche cet aide";
    say "  -p\t--planning\t\taffiche les derniers épisodes sortis pour l'utilisateur défini";
    say "  -s\t--search\t<série>\trecherche la série passée en paramètre";
    say "  -d\t--display\t<série>\trecherche la série passée en paramètre et affiche la description";

    say "\nUtilise l'API du site BetaSeries (https://www.betaseries.com/)";
    say "Documentation : https://www.betaseries.com/dev/wiki/Documentation";

    exit;
}

sub list {
    my @series = SeriesSource::betaseries->get_series(time()-24*3600);

    if(scalar(@series) == 0) {
        say "Aucune série trouvée pour le compte Dev002";
    }
    else {
        foreach my $serie (@series) {
            print $serie->{name}," S",$serie->{season}," E",$serie->{episode},"\n";
        }
    }
}

sub search {
    my @series = SeriesSource::betaseries->search($search);

    say scalar(@series), " séries trouvées\n";
    foreach my $serie (@series) {
        say $serie->{title}, " (",$serie->{url},")";
    }
}

sub display {
    SeriesSource::betaseries->display($display);
}

init;

if (defined $list) {
    list;
}
elsif (defined $search) {
    search;
}
elsif (defined $display) {
    display;
}
else {
    help;
}

SeriesSource::betaseries->disconnect;

exit 0;
