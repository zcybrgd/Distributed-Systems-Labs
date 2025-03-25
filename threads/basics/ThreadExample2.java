package threads.basics;  
//la 2eme methode pour créer un thread
class Worker implements Runnable {
    public void run() {
        System.out.println("Thread en exécution");
    }
}

public class ThreadExample2 {  
    public static void main(String[] args) {  
        Thread t = new Thread(new Worker());  
        t.start(); //on démarre le thread  
    }  
} 
