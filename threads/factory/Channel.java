package threads.factory;

public interface Channel {
    void send(Object item);   //method to add an item to the buffer
    Object receive();         //method to retrieve an item from the buffer
}


