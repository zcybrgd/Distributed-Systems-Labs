#include "calcul.h"

void *
calcul_null_1_svc(void *argp, struct svc_req *rqstp) {
    static char* result;
    return (void*) &result;
}

reponse *
calcul_addition_1_svc(data *argp, struct svc_req *rqstp) {
    static reponse result;
    unsigned int max;

    result.errno = 0;
    max = argp->arg1 > argp->arg2 ? argp->arg1 : argp->arg2;
    result.somme = argp->arg1 + argp->arg2;

    if (result.somme < max) {
        result.errno = 1; //overflow détecté
    }

    return &result;
}
