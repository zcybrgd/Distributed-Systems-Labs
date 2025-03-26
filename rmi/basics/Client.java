import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.getRegistry("127.0.0.1", 1099);
            Addition stub = (Addition) registry.lookup("AdditionService");
            int result = stub.add(5, 3);
            System.out.println("Résultat : " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
