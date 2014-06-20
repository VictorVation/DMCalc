package com.victorszeto.dmcalc;

import java.math.BigInteger;

/**
 * Created by timothytong on 2014-06-18.
 */
import java.util.ArrayList;

public class RSSolver {
    private static BigInteger expo = new BigInteger("1");
    private static BigInteger a;
    private static BigInteger r;
    private static BigInteger b;
    private static BigInteger m;
    private static int counter = 1;

    public static BigInteger repeatedSquare(BigInteger base, BigInteger exponent, BigInteger mod) {
        a= base;
        b = exponent;
        m = mod;
        r = a.mod(m);
        ArrayList<BigInteger> remainders = new ArrayList<BigInteger>();
        remainders.add(new BigInteger("1"));
        remainders.add(r);
        counter++;
        if (a == m) {
            r = BigInteger.valueOf(0l);
        } else if (a.mod(m) == BigInteger.valueOf(1l)) {
            r = BigInteger.valueOf(1l);
        } else {
            while (!expo.equals(b) ) {

                if ((expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == -1 || (expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == 0) {
                    expo = expo.multiply(BigInteger.valueOf(2l));
                    r = (r.multiply(r)).mod(m);
                   System.out.println("exp = " + expo + ", remainder = " + r);
                    remainders.add(r);
                    counter++;
                } else {
                    for (int i = counter; i >= 0; i--) {
                        if (expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == -1 || expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == 0) {
                            expo = expo.add(BigInteger.valueOf((long)Math.pow(2, i)));
                            counter++;
                            r = (r.multiply(remainders.get(i+1))).mod(m);

                            System.out.println("exp = " + expo + ", remainder = " + r);
                        }
                    }
                }
            }
        }
        return r;
    }
}
