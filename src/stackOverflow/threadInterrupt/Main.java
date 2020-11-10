/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Main.java 100473
 * 10-Nov-20 13:15:49Z Oleksii_Morenets $
 */

package stackOverflow.threadInterrupt;

public class Main {

    public static void main(String[] args) {
        Horse horsik = new Horse();
        horsik.horseStop();
    }

    public static class Horse implements Runnable {
        Thread t;

        public Horse() {
            this.t = new Thread("Anna");
            this.t.start();
        }

        public void horseStop() {
            String trName = this.t.getName();
            System.out.println(trName);
            this.t.interrupt();
        }

        @Override
        public void run() {
            while (!Thread.interrupted()) {
            }
        }
    }
}
