/*
 * Copyright 2007-2020 Thomson Reuters Global Resources. All rights reserved. Proprietary and confidential information of TRGR.
 * Disclosure, use, or reproduction without the written authorisation of TRGR is prohibited. : Main.java 100473
 * 01-Dec-20 21:38:48Z Oleksii_Morenets $
 */

package stackOverflow.dataReadFromFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Main {
    static String[] name = new String[100];
    static int[] score = new int[100];

    public static void main(String[] args) throws IOException {
        new Main().readFile();
        System.out.println(Arrays.toString(name));
        System.out.println(Arrays.toString(score));
    }

    void readFile() throws IOException
    {
        File file = new File("text.txt");
        try (FileReader fr = new FileReader(file);
             BufferedReader reader = new BufferedReader(fr))
        {
            String temp;
            int index = 0;
            while ((temp = reader.readLine()) != null)
            {
                String[] parts = temp.split(";");
                Main.name[index] = parts[0];
                Main.score[index] = Integer.parseInt(parts[1]);
                index++;
            }
        }
    }
}
