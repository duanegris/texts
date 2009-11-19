#!/bin/sh

read x op y

case $op in
    "+") echo `expr $x + $y`;;
    "-") echo `expr $x - $y`;;
    "*") echo `expr $x \* $y`;;
    "/") echo `expr $x / $y`;;
    *) echo "erreur de syntaxe"
esac
