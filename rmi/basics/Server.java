import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {
    public static void main(String[] args) {
        try {
            AdditionImpl obj = new AdditionImpl();
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("AdditionService", obj);
            System.out.println("Serveur RMI en attente...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
