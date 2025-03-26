import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class AdditionImpl extends UnicastRemoteObject implements Addition {
    protected AdditionImpl() throws RemoteException {
        super();
    }

    public int add(int a, int b) throws RemoteException {
        return a + b;
    }
}
