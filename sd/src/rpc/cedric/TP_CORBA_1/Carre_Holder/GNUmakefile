IMPL=$(INTERFACE)Impl
IDL=idlj

all: $(INTERFACE:=.class) $(SOURCES:=.class)


$(INTERFACE:=.class) : $(INTERFACE:=.idl)
	$(IDL) -fall -oldImplbase $(INTERFACE:=.idl)
	javac $(INTERFACE:=.java)

%.class : %.java
	javac $*.java


clean :
	/bin/rm -f $(INTERFACE)H*.java $(INTERFACE)POA.java $(INTERFACE)Operations.java _$(INTERFACE)*.java $(INTERFACE).h $(INTERFACE).java *.class *~ 2> /dev/null

