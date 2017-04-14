package concurrent_programming;

public class Buffer {
    public int letters = 0, digits = 0, symbols = 0;
    private String msg; // Message sent from producer to consumer.
    private boolean empty = true;
    private boolean consumer1 = false, consumer2 = false, consumer3 = false;

    public synchronized void put(String msg) {
        while (!empty) { // Wait until message has been retrieved.
            try {
                wait();
            } catch (InterruptedException e) {
            } // Never invoke the wait method outside of a loop!
        }
//	System.out.println(Thread.currentThread().getName() + " puts " + msg);
//	System.out.println("zerowanie");
        consumer1 = false;
        consumer2 = false;
        consumer3 = false;
        this.msg = msg; // Store message.
        empty = false;
        notifyAll(); // Notify consumer that status has changed.
    }

    public synchronized String take() {
        if (Thread.currentThread().getName().equals("1")) {
            consumer1 = true;
//	    System.out.println("consumer1 = true");
        } else if (Thread.currentThread().getName().equals("2")) {
            consumer2 = true;
//	    System.out.println("consumer2 = true");
        } else if (Thread.currentThread().getName().equals("3")) {
            consumer3 = true;
//	    System.out.println("consumer3 = true");
        }

        if (consumer1 && consumer2 && consumer3) {
            empty = true;
            notifyAll(); // Notify producer that status has changed.
//	    System.out.println("empty = true");
        }

        while (empty || (Thread.currentThread().getName().equals("1") && consumer1)
                || (Thread.currentThread().getName().equals("2") && consumer2)
                || (Thread.currentThread().getName().equals("3") && consumer3)) { // Wait until message is available.
            try {
                wait();
            } catch (InterruptedException e) {
            } // Never invoke the wait method outside of a loop!
        }
        System.out.println(Thread.currentThread().getName() + " takes " + msg);
        return msg;
    }
}