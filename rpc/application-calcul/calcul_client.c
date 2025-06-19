//donc le but ici c que le client appelle une fonction comme si elle était locale, mais elle s’exécute sur le serveur à distance
//RPC va nous masquer la complexité réseau: no besoin de créer des sockets, de gérer l’envoi manuel des messages, ni la sérialisation/désérialisation des données 
#include <limits.h>
#include "calcul.h"

CLIENT *clnt; //structure contenant la connexion vers le serveur

void test_addition(uint param1, uint param2) {
    reponse *resultat;
    data parametre;
    //préparation des arguments (typical)
    parametre.arg1 = param1;
    parametre.arg2 = param2;

    printf("Appel de CALCUL_ADDITION avec: %u et %u\n", parametre.arg1, parametre.arg2);
    //un appel réseau UDP avec sérialisation automatique
    resultat = calcul_addition_1(&parametre, clnt);
    if (resultat == NULL) {
        clnt_perror(clnt, "Échec appel distant");
        clnt_destroy(clnt);
        exit(EXIT_FAILURE);
    }

    if (resultat->errno == 0) {
        printf("Résultat = %u\n\n", resultat->somme);
    } else {
        printf("Erreur: overflow détecté\n\n");
    }
}

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s serveur_host\n", argv[0]);
        exit(1);
    }
    //on établit une connex RPC avec CALCUL, VERSION_UN → viennent du fichier .x et on choisit UDP, mais ça peut être "tcp" aussi
    //elle crée le stub client
    clnt = clnt_create(argv[1], CALCUL, VERSION_UN, "udp");
    if (clnt == NULL) {
        clnt_pcreateerror(argv[1]);
        exit(1);
    }
     // test des 2 cas 
    test_addition(UINT_MAX - 15, 10);
    test_addition(UINT_MAX, 10);

    clnt_destroy(clnt);
    return 0;
}
