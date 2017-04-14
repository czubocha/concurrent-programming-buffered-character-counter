package concurrent_programming;

public class Consumer_3 extends Thread {
    private Buffer buf;

    public Consumer_3(String name, Buffer buf) {
        super(name);
        this.buf = buf;
    }

    @Override
    public synchronized void run() {
        String msg = "";
        do {
            msg = buf.take();
            for (int i = 0; i < msg.length(); i++)
                if (msg.charAt(i) >= 48 && msg.charAt(i) <= 57)
                    buf.digits++;
            // System.out.println("Other digits: " + buf.otherDigits);
        } while (!msg.equals(" "));

    }
}
