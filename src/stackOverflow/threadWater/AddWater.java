/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : AddWater.java 100473
 * 10-Nov-20 15:09:55Z Oleksii_Morenets $
 */

package stackOverflow.threadWater;

public class AddWater extends Thread {
    private Cup cup;

    public AddWater(Cup cup) {
        this.cup = cup;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            if (cup.getCap() <= 0) {
                break;
            }
            cup.add();

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                System.out.println("catch InterruptedException");
                break;
            }
        }
        System.out.println("time = " + cup.getTime());
    }
}
