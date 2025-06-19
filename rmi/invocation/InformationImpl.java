//implémentation de l'objet distant

package com.jmdoudoux.test.rmi;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// classe distante implémentant l'interface Information
public class InformationImpl extends UnicastRemoteObject implements Information {
    private static final long serialVersionUID = 2674880711467464646L;
    // constructeur nécessaire pour UnicastRemoteObject
    protected InformationImpl() throws RemoteException {
        super(); // init le support pour l'appel distant
    }
    // implémentation de la méthode distante
    public String getInformation() throws RemoteException {
        System.out.println("Invocation de getInformation() depuis le client");
        return "hello whatever"; 
    }
}
