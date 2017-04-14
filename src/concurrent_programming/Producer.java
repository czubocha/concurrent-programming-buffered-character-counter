package concurrent_programming;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Producer extends Thread {
    private Buffer buf;
    private Scanner scan;

    public Producer(String name, Buffer buf, String fileName) {
        super(name);
        this.buf = buf;

        try {
            scan = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println("File not found!");
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        while (scan.hasNextLine())
            buf.put(scan.nextLine());
        buf.put(" ");
    }
}
