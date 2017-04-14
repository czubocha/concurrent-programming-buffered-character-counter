package concurrent_programming;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Buffer buf = new Buffer();
        Thread producer = new Producer("Producer", buf, "src/concurrent_programming/Buffer.java");
        Thread consumer1 = new Consumer_1("1", buf);
        Thread consumer2 = new Consumer_2("2", buf);
        Thread consumer3 = new Consumer_3("3", buf);

        producer.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();

        producer.join();
        consumer1.join();
        consumer2.join();
        consumer3.join();

        System.out.printf("%-10s%d\n%-10s%d\n%-10s%d\n", "Letters: ", buf.letters, "Symbols: ", buf.symbols, "Digits: ", buf.digits);
    }

}
