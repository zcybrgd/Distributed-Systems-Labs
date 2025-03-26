package threads.factory;
import java.util.Date;
class Consumer implements Runnable
{
private Channel mbox;
public Consumer(Channel mbox) {
this.mbox = mbox;
}
public void run() {
Date message;
while (true) {
SleepUtilities.nap();
// consume an item from the buffer
System.out.println("Consumer wants to consume.");
message = (Date)mbox.receive(); // to retrive messages from the buffer
if (message != null)
System.out.println("Consumer consumed " + message);
}
}
}
