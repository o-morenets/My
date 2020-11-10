/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Cup.java 100473
 * 10-Nov-20 15:03:19Z Oleksii_Morenets $
 */

package stackOverflow.threadWater;

public class Cup {
    int cap = 100;
    int time = 0;

    public synchronized int getCap() {
        return cap;
    }

    public synchronized int getTime() {
        return time;
    }

    public synchronized void add() {
        while (cap >= 100) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        cap += 10;
        System.out.println("+ (" + cap + ")");
        time += 6;
        notify();
    }

    public synchronized int drink() {
/*
        while (cap <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
*/

        cap -= 10;
        System.out.println("- (" + cap + ")");
        time += 3;
//        plus.interrupt();
        notify();

        return cap;
    }
}
