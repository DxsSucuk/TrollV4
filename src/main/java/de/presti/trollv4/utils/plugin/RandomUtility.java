package de.presti.trollv4.utils.plugin;

import java.util.Random;

public class RandomUtility {
    public static String getRandomID() {
        StringBuilder str = new StringBuilder();
        int lastrandom = 0;
        for (int i = 0; i < 9; i++) {
            Random r = new Random();
            int rand = r.nextInt(9);
            while (rand == lastrandom) {
                rand = r.nextInt(9);
            }
            lastrandom = rand;
            str.append(rand);
        }
        return str.toString();
    }
}
