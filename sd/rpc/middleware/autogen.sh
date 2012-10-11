#! /bin/sh
#######################################################
#### rebuild file with autoconf & automake
#######################################################

rm -f config.cache
rm -f config.log
rm -f config.status
rm -f config.h*

echo "#### aclocal"
aclocal
echo "#### run autoheader"
autoheader
test -f "config.h.in:config.h.incl:config.h.decl" && \
    mv "config.h.in:config.h.incl:config.h.decl" config.h.in
echo "#### run autoconf"
autoconf
echo "#### run automake"
touch config.h.incl config.h.decl
automake -a

