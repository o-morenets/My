package stackOverflow.threadWater;

public class Main {

    static int cap = 100;
    static int time = 0;

    static Thread plus;

    public static void main(String[] args) {

        Thread drink = new Thread(new Runnable() {
            @Override
            public void run() {
                drink();
            }
        });

        plus = new Thread(new Runnable() {
            @Override
            public void run() {
                add();
            }
        });

        drink.start();
        plus.start();
    }

    private synchronized static void add() {
        while (!Thread.interrupted() && cap > 0) {
            try {
                Thread.sleep(400);
            } catch (InterruptedException e) {
                break;
            }
            cap += 10;
            System.out.println("+ (" + cap + ")");
            time += 6;
        }
    }

    private synchronized static void drink() {
        while (cap > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ignored) {
            }
            cap -= 10;
            System.out.println("- (" + cap + ")");
            time += 3;
        }
        plus.interrupt();
        System.out.println(time);
    }

}
