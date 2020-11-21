/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Main.java 100473
 * 20-Nov-20 7:41:14Z Oleksii_Morenets $
 */

package stackOverflow.base64;

import java.util.ArrayList;
import java.util.Base64;

public class Main {
    public static String encode(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static String encodeWithoutPadding(byte[] bytes) {
        return Base64.getEncoder()
                .withoutPadding()
                .encodeToString(bytes);
    }

    public static byte[] decode(String s) {
        return Base64.getDecoder().decode(s);
    }

    public static void main(String[] args) {
        String s = "Hello, World!";
        String encoded = encodeWithoutPadding(s.getBytes());
        String decoded = new String(decode(encoded));
        System.out.println(encoded);
        System.out.println(decoded);

        ArrayList<Integer> integerArrayList = new ArrayList<>();
        new ArrayList<>(integerArrayList);
    }
}