package com.jmdoudoux.test.rmi;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class LanceServeur {
    public static void main(String[] args) {
        try {
            //1. Démarrer le registre RMI sur le port 1099
            LocateRegistry.createRegistry(1099);

            //2. créer l'objet distant
            InformationImpl info = new InformationImpl();

            //3. Créer l'URL de liaison
            String url = "rmi://" + InetAddress.getLocalHost().getHostAddress() + "/TestRMI";

            //4. Enregistrer l'objet distant sous ce nom dans le registre
            Naming.rebind(url, info);
            System.out.println("[Serveur] Enregistrement fait à l'adresse: " + url);
        } catch (RemoteException | MalformedURLException | UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
