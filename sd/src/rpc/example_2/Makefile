
PROJ=myservice

all: $(PROJ)_client $(PROJ)_server

$(PROJ)_client:$(PROJ)_client.o $(PROJ)_clnt.o $(PROJ)_xdr.o
	gcc -o $@ $^

$(PROJ)_server:$(PROJ)_server.o $(PROJ)_svc.o $(PROJ)_xdr.o
	gcc -o $@ $^

%.o: %.c
	gcc -c $<

clean:
	rm -f *.o

