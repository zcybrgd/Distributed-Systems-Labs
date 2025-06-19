#include "calcul.h" //généré par le idl

// vide juste pour tester si le service est en ligne et sert aussi à réveiller le serveur
void *
calcul_null_1_svc(void *argp, struct svc_req *rqstp) {
    static char* result;
    return (void*) &result;
}

//la fonction la raison du remote call actually
//argp contient les données reçues (arg1, arg2)
reponse *
calcul_addition_1_svc(data *argp, struct svc_req *rqstp) {
    static reponse result;
    //static : les données persistent après le retour (important car RPC retourne un pointeur)
    unsigned int max;

    result.errno = 0;
    max = argp->arg1 > argp->arg2 ? argp->arg1 : argp->arg2;
    result.somme = argp->arg1 + argp->arg2;

    if (result.somme < max) {
        result.errno = 1; //overflow détecté
    }

    return &result;
}

//rpcinfo interroge le portmapper pour vérifier que le service RPC est actif
