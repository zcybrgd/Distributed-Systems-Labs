// lire des threads

class JoinableWorker implements Runnable
{
public void run() {
System.out.println("Worker working");
}
}
public class JoinExample
{
public static void main(String[] args) {
Thread task = new Thread(new JoinableWorker());
task.start();
try { task.join(); }
catch (InterruptedException ie) { }
System.out.println("Worker done");
}
}

