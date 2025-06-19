package com.jmdoudoux.test.rmi;

import java.rmi.*;

// interface distante déclarant les méthodes que le client pourra appeler à distance
public interface Information extends Remote {
    // toute méthode distante doit lever RemoteException
    public String getInformation() throws RemoteException;
}
