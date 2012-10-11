
set -e
source $FOKON_DIR/exo-data-base/test-setup.bash
TD=$FED/middleware/test

##################################################
#### rpc1 

# generation of the executables
rpc1_serv()
{
    $RPCGEN rpc1.xdr
    set -x
    $RPCCC -o $HD/$1 $RPCCOPTS -DVALUE=${2:-100} -DINCR=${3:-1} \
        rpc1-$1.c rpc1_svc.c $RPCCCLIBS
    set +x
}

rpc1_clnt()
{
    $RPCGEN rpc1.xdr
    set -x
    $RPCCC -o $HD/$1$3 $RPCCOPTS -DVALUE=${2:-100} -DINCR=${3:-1} \
        rpc1-$1.c rpc1_clnt.c $RPCCCLIBS
    set +x
}

# run the questions
rpc1_q1()
{
    rpc1_serv serv $2 $3
    rpc1_clnt get  $2 ""
    rpc1_clnt inc  $2 1
    rpc1_clnt inc  $2 3
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1
}

##################################################
#### rpc2 

rpc2_q1()
{
    pwd
    $RPCGEN rpc2.xdr
    set -x
    $RPCCC -o $HD/serv $RPCCOPTS -DVALUE=${2:-100} -DINCR=${3:-100} \
        rpc2-serv.c rpc2_svc.c $RPCCCLIBS
    $RPCCC -o $HD/init  $RPCCOPTS rpc2-init.c rpc2_clnt.c $RPCCCLIBS
    $RPCCC -o $HD/essai $RPCCOPTS rpc2-essai.c rpc2_clnt.c $RPCCCLIBS
    set +x
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1
}

##################################################
#### rpc2 

rpc3_q1()
{
    pwd
    $RPCGEN rpc3.xdr
    set -x
    $RPCCC -o $HD/serv $RPCCOPTS -DVALUE=${2:-100} -DINCR=${3:-100} \
        rpc3-serv.c rpc3_svc.c $RPCCCLIBS
    $RPCCC -o $HD/init  $RPCCOPTS rpc3-init.c rpc3_clnt.c $RPCCCLIBS
    $RPCCC -o $HD/i2c $RPCCOPTS rpc3-i2c.c rpc3_clnt.c $RPCCCLIBS
    set +x
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1
}

##################################################
#### ob1 

# generation of the executables
ob1_generate()
{
    #$OBIDL ob1.idl
    $OBCXX -o $1 $OBCXXOPTS -DVALUE=${2:-100} -DPRECISION=${3:-2} \
        -I$FED/middleware/tmp ob1-$1.cc \
        $FED/middleware/tmp/ob1_skel.cc \
        $FED/middleware/tmp/ob1.cc $OBCXXLIBS
}

# run the questions
ob1_q1()
{
    ob1_generate serv1 $2 $3
    pkill serv1 || true
    ./serv1 > 1 &
    sleep 1
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1 $(cat 1) 
    pkill serv1 || true
}

ob1_q2()
{
       ob1_generate serv2 $2 $3
       pkill nameserver || true
       nameserv -i -OAport 5454 &
       sleep 1
       ./serv2 -ORBservice NameService \
            iiop://localhost:5454/DefaultNamingContext &
       sleep 1
       $WD/bin/fokontest -dir $HD -pq $1
       $WD/bin/fokontest -dir $HD -sq $1 5454 
       pkill nameserv
       pkill serv3 || true
}

ob1_q3()
{
       ob1_generate serv3 
       pkill nameserver || true
       nameserv -i -OAport 5454 &
       sleep 1
       $WD/bin/fokontest -dir $HD -pq $1
       $WD/bin/fokontest -dir $HD -sq $1 $(pwd)/serv3 5454 
       pkill nameserv
       pkill serv3 || true
}

##################################################
#### ob2 

# generation of the executables
ob2_generate()
{
    #$OBIDL ob2.idl
    $OBCXX -o $1 $OBCXXOPTS -DOB2_VC=${2:-100} -DOB2_VC2=${3:-100} \
        -I$FED/middleware/tmp ob2-$1.cc \
        $FED/middleware/tmp/ob2_skel.cc \
        $FED/middleware/tmp/ob2.cc $OBCXXLIBS
}

# run the questions
ob2_q1()
{
    ob2_generate serv1 $2 $3
    pkill serv1 || true
    ./serv1 > 1 &
    sleep 1
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1 $(cat 1) 
    pkill serv1 || true
}

ob2_q2()
{
       ob2_generate serv2 $2 $3
       pkill nameserver || true
       nameserv -i -OAport 5454 &
       sleep 1
       ./serv2 -ORBservice NameService \
            iiop://localhost:5454/DefaultNamingContext &
       sleep 1
       $WD/bin/fokontest -dir $HD -pq $1
       $WD/bin/fokontest -dir $HD -sq $1 5454 
       pkill nameserv
       pkill serv3 || true
}

##################################################
#### ob3 

# generation of the executables
ob3_generate()
{
    #$OBIDL ob3.idl
    $OBCXX -o $1 $OBCXXOPTS -DOB2_VC=${2:-100} -DOB2_VC2=${3:-100} \
        -I$FED/middleware/tmp ob3-$1.cc \
        $FED/middleware/tmp/ob3_skel.cc \
        $FED/middleware/tmp/ob3.cc $OBCXXLIBS
}

# run the questions
ob3_q1()
{
    ob3_generate serv1 $2 $3
    pkill serv1 || true
    ./serv1 > 1 &
    sleep 1
    $WD/bin/fokontest -dir $HD -pq $1
    $WD/bin/fokontest -dir $HD -sq $1 $(cat 1) 
    pkill serv1 || true
}

ob3_q2()
{
       ob3_generate serv2 $2 $3
       pkill nameserver || true
       nameserv -i -OAport 5454 &
       sleep 1
       ./serv2 -ORBservice NameService \
            iiop://localhost:5454/DefaultNamingContext &
       sleep 1
       $WD/bin/fokontest -dir $HD -pq $1
       $WD/bin/fokontest -dir $HD -sq $1 5454 
       pkill nameserv
       pkill serv3 || true
}

##################################################
#### with no args initialisation
if test $# = 0 ; then
    main_init
    exit 0
fi

# main switch
chgdir
case $1 in 
    1)   rpc1_q1 1 $2 $3 ; ;;
    2)   rpc2_q1 2 $2 $3 ; ;;
    3)   rpc3_q1 3 $2 $3 ; ;;
    4)   ob2_q1 4 $2 $3 ; ;;
    5)   ob2_q2 5 $2 $3 ; ;;
    6)   ob3_q1 6 $2 $3 ; ;;
    7)   ob3_q2 7 $2 $3 ; ;;
    8.1) ob1_q1 $1 $2 $3 ; ;;
    8.2) ob1_q2 $1 $2 $3 ; ;;
    8.3) ob1_q3 $1 ; ;;
    all) rpc1_q1 1 $2 $3 
         rpc2_q1 2 $2 $3
         rpc3_q1 3 $2 $3
         ob2_q1 4 $2 $3
         ob2_q2 5 $2 $3
         ob3_q1 6 $2 $3 
         ob3_q2 7 $2 $3
         ob1_q1  8.1 $2 $3
         ob1_q2  8.2 $2 $3
         ob1_q3  8.3 ;
         ;;
esac

##################################################
# normal end
exit 0
##################################################


