package com.jmdoudoux.test.rmi;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public class LanceClient {
    public static void main(String[] args) {
        System.out.println("Lancement du client");
        try {
            //1. Chercher l'objet distant avec son URL dans le registre RMI (simple addresse locale comme mentionné dans l'énoncé du TP)
            Remote r = Naming.lookup("rmi://127.0.0.1/TestRMI");
            //2. Vérifier le type et caster en Information
            if (r instanceof Information) {
                String s = ((Information) r).getInformation();
                System.out.println("Réponse reçue = " + s);
            }
        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        System.out.println("Fin du client");
    }
}
