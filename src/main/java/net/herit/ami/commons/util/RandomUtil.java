package net.herit.ami.commons.util;

import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class RandomUtil {
    private SecureRandom sr;

    public RandomUtil() {
        sr = new SecureRandom();
    }

    public int nextInt() {
        return sr.nextInt();
    }

    public int nextInt(int bound) {
        return sr.nextInt(bound);
    }

    public float nextFloat(int bound) {
        return sr.nextFloat() * bound;
    }

    public double nextDouble(int bound, int length) {
        int d = (int)Math.pow(10, length);
        return Math.floor((sr.nextFloat() * bound)*d)/d;
    }
}
