/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Main.java 100473
 * 11-Nov-20 11:21:58Z Oleksii_Morenets $
 */

package stackOverflow.genericMethod;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> integerList = Arrays.asList(1, 2, 3);
        Gen gen = new Gen<>();
        gen.m(integerList);
    }

    static class Gen<T> {
        <T> void m(Collection<T> collection) {
            for (T s : collection) {
                System.out.println(s + " Дженерик");
            }
        }

        <T> void m(List<String> list) {
            for (String s : list) {
                System.out.println(s + " String");
            }
        }
    }
}
