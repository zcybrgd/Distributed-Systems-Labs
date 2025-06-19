all: server client

calcul.h calcul_clnt.c calcul_svc.c calcul_xdr.c:
	rpcgen -a calcul.x

server: calcul_server.o calcul_svc.o calcul_xdr.o
	gcc -o server calcul_server.o calcul_svc.o calcul_xdr.o

client: calcul_client.o calcul_clnt.o calcul_xdr.o
	gcc -o client calcul_client.o calcul_clnt.o calcul_xdr.o

calcul_server.o: calcul_server.c calcul.h
	gcc -c calcul_server.c

calcul_client.o: calcul_client.c calcul.h
	gcc -c calcul_client.c

calcul_svc.o: calcul_svc.c calcul.h
	gcc -c calcul_svc.c

calcul_clnt.o: calcul_clnt.c calcul.h
	gcc -c calcul_clnt.c

calcul_xdr.o: calcul_xdr.c calcul.h
	gcc -c calcul_xdr.c

clean:
	rm -f *.o client server calcul_clnt.c calcul_svc.c calcul_xdr.c calcul.h
