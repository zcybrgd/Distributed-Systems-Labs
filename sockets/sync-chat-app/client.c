// le but ici est de créer un chat basique en utilisant les sockets (not realistic just to learn sockets in c)

/**
basically voici le fonctionnement: 
le client écrit un message (peut pas faire plusieurs successifs)
le serveur le lit et répond
Répéter jusqu’à ce que l’un des deux tape "EXIT"
la communication donc est synchrone, un message chacun à tour de rôle
*/
#include <sys/socket.h>  // pour socket(), connect(), send(), recv(), shutdown()
#include <netinet/in.h> // pour sockaddr_in
#include <stdio.h> 
#include <stdlib.h>
#include <string.h>
#include <arpa/inet.h> // pour inet_addr()
#include <unistd.h>

int main() {
    int socketID;
    struct sockaddr_in informations; //pour spécifier les informations du serveur (le port de connexion et l'adresse IP sur laquelle il attendra la connexion du client)
    char phrase[255]; // elle contiendra le message

    // configuration du serveur (adresse + port)
    informations.sin_family = AF_INET; // Famille d'adresses IPv4
    informations.sin_port = htons(6666); //Port 6666 (htons convertit en big-endian)
    informations.sin_addr.s_addr = inet_addr("127.0.0.1");// ip du serveur 

    // Création du socket !! le point d'entrée de la connexion renvoyé par la fonction socket
    socketID = socket(AF_INET, SOCK_STREAM, 0);  // SOCK_STREAM => TCP
    if (socketID == -1) {
        perror("socket");
        exit(-1);
    }

    // connexion au serveur
    if (connect(socketID, (struct sockaddr*) &informations, sizeof(struct sockaddr_in)) == -1) {
        perror("connect");
        exit(-1);
    }

    printf("Connecté au serveur. Tapez 'EXIT' pour quitter.\n");

    // Communication
    do {
        printf("Client > ");
        fgets(phrase, sizeof(phrase), stdin);//lire une ligne du texte
        phrase[strcspn(phrase, "\n")] = '\0'; // supprimer le \n

        //Envoi
        if (send(socketID, phrase, strlen(phrase), 0) == -1) {
            perror("send");
        }

        // Réception
        if (strcmp(phrase, "EXIT") != 0) {
            memset(phrase, 0, 255); //on réinitialise le buffer qui contient le message du client
            recv(socketID, phrase, 255, 0);  // Reçoit la réponse du serveur
            printf("Serveur > %s\n", phrase);
        }

    } while (strcmp(phrase, "EXIT") != 0);

    //fermeture gracefully de la socket
    shutdown(socketID, SHUT_RDWR);
    return 0;
}
