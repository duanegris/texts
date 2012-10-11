
tmp/ob1.h tmp/ob1.cc \
tmp/ob1_skel.h tmp/ob1_skel.cc: ob1.idl
	mkdir -p tmp ; cd tmp ; idl ../ob1.idl
	mv tmp/ob1.cpp      tmp/ob1.cc
	mv tmp/ob1_skel.cpp tmp/ob1_skel.cc

tmp/ob2.h tmp/ob2.cc \
tmp/ob2_skel.h tmp/ob2_skel.cc: ob2.idl
	mkdir -p tmp ; cd tmp ; idl ../ob2.idl
	mv tmp/ob2.cpp      tmp/ob2.cc
	mv tmp/ob2_skel.cpp tmp/ob2_skel.cc

tmp/ob3.h tmp/ob3.cc \
tmp/ob3_skel.h tmp/ob3_skel.cc: ob3.idl
	mkdir -p tmp ; cd tmp ; idl ../ob3.idl
	mv tmp/ob3.cpp      tmp/ob3.cc
	mv tmp/ob3_skel.cpp tmp/ob3_skel.cc

lib_LIBRARIES=libmiddleware_gen.a libmiddleware_run.a

libmiddleware_gen_a_SOURCES = \
    middleware.cc \
    tmp/ob1.cc tmp/ob1_skel.h tmp/ob1_skel.cc \
    tmp/ob2.cc tmp/ob2_skel.h tmp/ob2_skel.cc \
    tmp/ob3.cc tmp/ob3_skel.h tmp/ob3_skel.cc
libmiddleware_gen_a_CFLAGS   = $(AM_CFLAGS)   -DFOKON_GEN
libmiddleware_gen_a_CXXFLAGS = $(AM_CXXFLAGS) -DFOKON_GEN

libmiddleware_run_a_SOURCES = \
    middleware.cc \
    tmp/ob1.cc tmp/ob1_skel.h tmp/ob1_skel.cc \
    tmp/ob2.cc tmp/ob2_skel.h tmp/ob2_skel.cc \
    tmp/ob3.cc tmp/ob3_skel.h tmp/ob3_skel.cc
libmiddleware_run_a_CFLAGS   = $(AM_CFLAGS)   -DFOKON_TEST
libmiddleware_run_a_CXXFLAGS = $(AM_CXXFLAGS) -DFOKON_TEST


clean:
	rm -rf *.o tmp 
	make clean-am

EXTRA_DIST = middleware.cc test \
    rpc1.c rpc2.c rpc3.c \
    ob1.idl ob1.cc \
    ob2.idl ob2.cc \
    ob3.idl ob3.cc

dist-hook:
	rm -rf `find $(distdir)/doc -name CVS`
	rm -rf $(distdir)/tmp
