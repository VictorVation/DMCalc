package com.dmcalc.dmcalc;

import java.util.ArrayList;

/**
 * Created by timothytong on 2014-06-15.
 */
public class GCDCalc {
    private static int num1;
    private static int num2;
    private static ArrayList<Integer> quotients;
    public static int calculateGCD(int in1, int in2){
        quotients = new ArrayList<Integer>();
        num1 = Math.abs(in1);
        num2 = Math.abs(in2);
        if(in1 == 0 && in2 == 0){
            System.out.println("Both numbers are 0's");
            return -1;
        }
        if(in1 == 0 || in2 == 0){
            System.out.println("One of the numbers is 0");
            return 0;
        }
        if(in1 == in2){
            System.out.println("They are equal");
            return Math.abs(in1);

        }
        else if (in1 > in2) {
            num1 = in1;
            num2 = in2;
        }
        else{
            num1 = in2;
            num2 = in1;
        }
        int r = 1;
        int q;
        while(r != 0){
            q = num1 / num2;
            quotients.add(q);
            r = num1 % num2;
            num1 = num2;
            num2 = r;
        }
        return num1;
    }

    public static ArrayList<Integer> getQuotients(){
        return quotients;
    }
}
