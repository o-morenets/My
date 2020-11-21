/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Main.java 100473
 * 21-Nov-20 15:31:05Z Oleksii_Morenets $
 */

package stackOverflow.doubleToShort;

public class Main {
    public static void main(String[] args) {
        double x = 389889877779.89;

        long l = Double.doubleToRawLongBits(x);
        System.out.println(l);
        String s = Long.toBinaryString(l);
        System.out.println(s);

        System.out.println((short)x);
    }
}
