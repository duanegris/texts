
#----------------------------------------------
all : serveur client 
#----------------------------------------------
serveur : serveur.o xdr_entiers2.o
	gcc -o serveur serveur.o xdr_entiers2.o -lnsl
#----------------------------------------------
client : client.o xdr_entiers2.o
	gcc -o client client.o xdr_entiers2.o -lnsl
#----------------------------------------------
clean :
	/bin/rm -f *.o serveur client *~ core* pok* *.ps
