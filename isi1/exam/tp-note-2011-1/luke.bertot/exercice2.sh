#!/bin/bash

if ([ $# != 2 ]) then
    echo "usage $0 arg1 arg2";
    exit 1;
fi

if ([ -d $1 ]) then
    if ([ -e $2 ]) then
	echo "$2 existe dans le répertoire courrant";
	exit 2;
    else
	echo '#!/bin/bash' > $2
	echo 'echo "Bienveune `date +%d/%m/%y` $USER"' >> $2  #[SG] $USER ok
	chmod +x $2;
	echo "succes";
    fi
else
	echo "$1 n'est pas un répertoire existant";
	exit 2;
fi

exit 0;