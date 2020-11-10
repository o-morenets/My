/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Drink.java 100473
 * 10-Nov-20 15:08:53Z Oleksii_Morenets $
 */

package stackOverflow.threadWater;

public class DrinkWater extends Thread {
    private Cup cup;

    public DrinkWater(Cup cup) {
        this.cup = cup;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            int cap = cup.drink();
            if (cap <= 0) {
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        }
    }
}
