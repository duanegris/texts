#!/bin/bash

echo "Je ne sais pas faire cette question"
echo "Mais je peux chercher les fichiers du type *.dat :"
ls | grep .dat | cat
echo "Et je peux écrire la date :"
date "+%Y-%m-%d"