#----------------------------------------------
all : server client
#----------------------------------------------
server : server.c
	gcc -Wall -o server  server.c 
#----------------------------------------------
client : client.c 
	gcc -Wall -o client client.c -lrpcsvc 
#----------------------------------------------
clean :
	rm -f *.o server client *~ pok* *.ps
