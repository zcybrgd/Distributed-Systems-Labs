// le but ici est de créer un chat basique en utilisant les sockets (not realistic just to learn sockets in c)

/**
basically voici le fonctionnement: 
le client écrit un message (peut pas faire plusieurs successifs)
le serveur le lit et répond
Répéter jusqu’à ce que l’un des deux tape "EXIT"
la communication donc est synchrone, un message chacun à tour de rôle
*/

#include <sys/socket.h>
#include <netinet/in.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h>
#include <unistd.h>

int main() {
    int socketID, connexion;
    struct sockaddr_in information_server, information_client;
    socklen_t len = sizeof(struct sockaddr_in);
    char phrase[255];

    // Création du socket
    socketID = socket(AF_INET, SOCK_STREAM, 0);
    if (socketID == -1) {
        perror("socket");
        exit(-1);
    }

    // Initialisation
    memset(&information_server, 0, sizeof(struct sockaddr_in));
    information_server.sin_family = AF_INET;
    information_server.sin_port = htons(6666);
    information_server.sin_addr.s_addr = INADDR_ANY;

    // Bind
    if (bind(socketID, (struct sockaddr *) &information_server, sizeof(struct sockaddr)) == -1) {
        perror("bind");
        exit(-1);
    }

    // Écoute
    if (listen(socketID, 5) == -1) {
        perror("listen");
        exit(-1);
    }

    // Accept
    memset(&information_client, 0, sizeof(struct sockaddr_in));
    connexion = accept(socketID, (struct sockaddr *) &information_client, &len);
    if (connexion == -1) {
        perror("accept");
        exit(-1);
    }

    printf("Connexion acceptée de %s\n", inet_ntoa(information_client.sin_addr));

    // Communication
    do {
        memset(phrase, 0, 255);
        recv(connexion, phrase, 255, 0);
        printf("Client > %s\n", phrase);

        if (strcmp(phrase, "EXIT") != 0) {
            printf("Serveur > ");
            fgets(phrase, sizeof(phrase), stdin);
            phrase[strcspn(phrase, "\n")] = 0; // Supprimer \n
            send(connexion, phrase, strlen(phrase), 0);
        }

    } while (strcmp(phrase, "EXIT") != 0);

    // Fermeture
    shutdown(socketID, SHUT_RDWR);
    return 0;
}
