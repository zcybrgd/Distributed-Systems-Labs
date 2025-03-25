package threads.basics;  
class Worker extends Thread {
    public void run(){
        System.out.println("thread en exécution");
    }
}

public class ThreadExample {  
    public static void main(String[] args) {  
        Worker t = new Worker();
        t.start();
    }  
} 
