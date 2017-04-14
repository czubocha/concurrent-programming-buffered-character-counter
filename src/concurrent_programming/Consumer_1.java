package concurrent_programming;

public class Consumer_1 extends Thread {
    private Buffer buf;

    public Consumer_1(String name, Buffer buf) {
        super(name);
        this.buf = buf;
    }

    @Override
    public synchronized void run() {
        String msg = "";
        do {
            msg = buf.take();
            for (int i = 0; i < msg.length(); i++)
                if ((msg.charAt(i) >= 65 && msg.charAt(i) <= 90) || (msg.charAt(i) >= 97 && msg.charAt(i) <= 122))
                    buf.letters++;
//	     System.out.println("Letters: " + buf.letters);
        } while (!msg.equals(" "));

    }
}