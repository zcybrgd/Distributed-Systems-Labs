# RPC Calculator + Détection d’Overflow (SunRPC, UDP)

Ce projet implémente une **application RPC simple** en C avec **SunRPC** dans le cadre d'un TP initial pour nous introduire au RPC dans le module SYSD
Le client appelle une fonction distante `CALCUL_ADDITION()` qui additionne deux entiers non signés (`unsigned int`) et renvoie :
* leur **somme**
* un **code d'erreur** si l'addition provoque un overflow.

> Le transport réseau utilisé est **UDP** (par défaut). Mais on peut l'adapter en **TCP** en changeant un seul argument trouvé dans create_client

---

## Structure du projet

```
calcul_rpc/
├── calcul.x           # Interface IDL RPC
├── calcul_client.c    # Client RPC
├── calcul_server.c    # Serveur RPC
├── calcul_clnt.c      # Stub client (auto-généré avec rpcgen -a calcul.x)
├── calcul_svc.c       # Stub serveur (idem)
├── calcul_xdr.c       # Fonctions de sérialisation XDR
├── calcul.h           # Header généré
└── Makefile           # Compilation automatisée
```

---

## Compilation & Exécution

### 1. Générer les stubs RPC

```bash
rpcgen -a calcul.x
```

### 2. Compiler tout

```bash
make
```

### 3. Lancer le serveur

```bash
./server &
```

### 4. Lancer le client

```bash
./client localhost
```

---

## Résultat attendu

```text
Appel de CALCUL_ADDITION avec: 4294967280 et 10
Résultat = 4294967290

Appel de CALCUL_ADDITION avec: 4294967295 et 10
Erreur: overflow détecté
```

---
