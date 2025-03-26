package threads.factory;
import java.util.Date;
class Producer implements Runnable
{
private Channel mbox;
public Producer(Channel mbox) {
this.mbox = mbox;
}
public void run() {
Date message;
while (true) {
SleepUtilities.nap();
message = new Date();
System.out.println("Producer produced " + message);
// produce an item & enter it into the buffer
mbox.send(message);
}
}
}
