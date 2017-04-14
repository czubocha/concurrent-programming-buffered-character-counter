package concurrent_programming;

public class Consumer_2 extends Thread {
    private Buffer buf;

    public Consumer_2(String name, Buffer buf) {
        super(name);
        this.buf = buf;
    }

    @Override
    public synchronized void run() {
        String msg = "";
        do {
            msg = buf.take();
            for (int i = 0; i < msg.length(); i++)
                if ((msg.charAt(i) >= 33 && msg.charAt(i) <= 47) || (msg.charAt(i) >= 58 && msg.charAt(i) <= 64)
                        || (msg.charAt(i) >= 91 && msg.charAt(i) <= 96)
                        || (msg.charAt(i) >= 123 && msg.charAt(i) <= 126))
                    buf.symbols++;
//	     System.out.println("Symbols: " + buf.symbols);
        } while (!msg.equals(" "));

    }
}