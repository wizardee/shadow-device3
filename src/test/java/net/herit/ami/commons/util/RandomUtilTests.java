package net.herit.ami.commons.util;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RandomUtilTests {

    @Test
    public void testRandom() {
        RandomUtil randomUtil = new RandomUtil();

        for(int i=0; i<10; i++) {
            System.out.println("a");
            System.out.println(
                    new BigDecimal(randomUtil.nextDouble(2, 1)).setScale(3, RoundingMode.HALF_DOWN));
        }

        System.out.println((int)Math.pow(10, 3));

        float pie = 3.14159265358979F;
        System.out.println((float)Math.round(pie*100)/100.0);

        double a = 0.123222;
        System.out.println(a);
        BigDecimal big = new BigDecimal(a).setScale(3, RoundingMode.HALF_UP);
        System.out.println(big);
    }
}
