package com.victorszeto.dmcalc;

import java.math.BigInteger;
import java.util.HashMap;

/**
 * Created by timothytong on 2014-06-18.
 */
import java.util.ArrayList;

public class RSSolver {
    private BigInteger r;
    private ArrayList<String> remainderStrings;

    public RSSolver(BigInteger base, BigInteger exponent, BigInteger mod) {
        BigInteger expo = new BigInteger("1");
        BigInteger a;
        BigInteger b;
        BigInteger m;
        int counter = 1;
        HashMap<BigInteger,BigInteger> map;
        this.remainderStrings = new ArrayList<String>();
        map = new HashMap<BigInteger, BigInteger>();

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
            BigInteger lastRemainder = r;
            map.put(a.mod(m), BigInteger.ONE);
            while (!expo.equals(b) ) {
                if ((expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == -1 || (expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == 0) {
                    expo = expo.multiply(BigInteger.valueOf(2l));
                    r = (r.multiply(r)).mod(m);
//                    System.out.println("exp = " + expo + ", remainder = " + r);
                    remainders.add(r);
                    map.put(expo,r);
//                    System.out.println(String.format("(key: %d,value: %d)",r,expo));
                    counter++;
                    BigInteger previousRemainder;
                    if(counter>2){
                        previousRemainder = remainders.get(remainders.size() - 2);
                    }
                    else{
                        previousRemainder = a;
                    }
                    System.out.println(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,previousRemainder,remainders.get(remainders.size()-1)));
                    remainderStrings.add(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,previousRemainder,remainders.get(remainders.size()-1)));
                } else {
//                    System.out.println("PART 2");
                    for (int i = counter; i >= 0; i--) {
                        if (expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == -1 || expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == 0) {
                            expo = expo.add(BigInteger.valueOf((long)Math.pow(2, i)));
                            counter++;
                            r = (r.multiply(remainders.get(i+1))).mod(m);
                            map.put(r,expo);
                            remainders.add(r);
                            BigInteger previousRemainder = remainders.get(remainders.size()-2);
//                            System.out.println(String.format("fetching key-value pair key %d",BigInteger.valueOf((long)Math.pow(2,i))));
                            remainderStrings.add(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,map.get(BigInteger.valueOf((long)Math.pow(2,i))),remainders.get(remainders.size()-1)));
                            System.out.println(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,map.get(BigInteger.valueOf((long)Math.pow(2,i))),remainders.get(remainders.size()-1)));
//                            System.out.println("exp = " + expo + ", remainder = " + r);
                        }
                    }
                }
            }
        }
    }
//    public static BigInteger repeatedSquare(BigInteger base, BigInteger exponent, BigInteger mod) {
//        map = new HashMap<BigInteger, BigInteger>();
//        a= base;
//        b = exponent;
//        m = mod;
//        r = a.mod(m);
//        ArrayList<BigInteger> remainders = new ArrayList<BigInteger>();
//        remainders.add(new BigInteger("1"));
//        remainders.add(r);
//        counter++;
//        if (a == m) {
//            r = BigInteger.valueOf(0l);
//        } else if (a.mod(m) == BigInteger.valueOf(1l)) {
//            r = BigInteger.valueOf(1l);
//        } else {
//            BigInteger lastRemainder = r;
//            map.put(a.mod(m), BigInteger.ONE);
//            while (!expo.equals(b) ) {
//                if ((expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == -1 || (expo.multiply(BigInteger.valueOf(2l))).compareTo(b) == 0) {
//                    expo = expo.multiply(BigInteger.valueOf(2l));
//                    r = (r.multiply(r)).mod(m);
////                    System.out.println("exp = " + expo + ", remainder = " + r);
//                    remainders.add(r);
//                    map.put(expo,r);
////                    System.out.println(String.format("(key: %d,value: %d)",r,expo));
//                    counter++;
//                    BigInteger previousRemainder;
//                    if(counter>2){
//                        previousRemainder = remainders.get(remainders.size() - 2);
//                    }
//                    else{
//                        previousRemainder = a;
//                    }
//                    System.out.println(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,previousRemainder,remainders.get(remainders.size()-1)));
//                    remainderStrings.add(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,previousRemainder,remainders.get(remainders.size()-1)));
//                } else {
////                    System.out.println("PART 2");
//                    for (int i = counter; i >= 0; i--) {
//                        if (expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == -1 || expo.add(BigInteger.valueOf((long) Math.pow(2, i))).compareTo(b) == 0) {
//                            expo = expo.add(BigInteger.valueOf((long)Math.pow(2, i)));
//                            counter++;
//                            r = (r.multiply(remainders.get(i+1))).mod(m);
//                            map.put(r,expo);
//                            remainders.add(r);
//                            BigInteger previousRemainder = remainders.get(remainders.size()-2);
////                            System.out.println(String.format("fetching key-value pair key %d",BigInteger.valueOf((long)Math.pow(2,i))));
//                            remainderStrings.add(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,map.get(BigInteger.valueOf((long)Math.pow(2,i))),remainders.get(remainders.size()-1)));
//                            System.out.println(String.format("%d^%d≡%d x %d≡ %d",a,expo,previousRemainder,map.get(BigInteger.valueOf((long)Math.pow(2,i))),remainders.get(remainders.size()-1)));
////                            System.out.println("exp = " + expo + ", remainder = " + r);
//                        }
//                    }
//                }
//            }
//        }
//        return r;
//    }

    public BigInteger getRemainder() {
        return this.r;
    }
    public ArrayList<String> getRemainderStrings(){
        return this.remainderStrings;
    }
}
