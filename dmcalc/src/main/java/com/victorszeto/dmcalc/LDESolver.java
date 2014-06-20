package com.victorszeto.dmcalc;

import java.util.ArrayList;

/**
 * Created by timothytong on 2014-06-15.
 */
public class LDESolver {
    public static int gcd;
    public static int xCo1;
    public static int yCo1;
    public static int xCo2;
    public static int yCo2;
    public static int xCo3;
    public static int yCo3;
    public static int[] solveLDE(int x, int y, int z){
        int[] results;
        GCDCalc gcdCalc = new GCDCalc();
        gcd = gcdCalc.calculateGCD(x,y);
        if(z % gcd != 0){
            return null;
        }
        else{
            ArrayList<Integer> quotients = gcdCalc.getQuotients();
            int multiplier = z / gcd;
            //        NSLog(@"%d",[quotients count]);
            if (quotients.isEmpty()) {
                return null;
            }
            else{
                if(x > y){
                    xCo1 = 0;
                    yCo1 = 1;
                    xCo3 = 1;
                    yCo3 = -1 * quotients.get(0);
                    //                NSLog(@"xCo3: %d yCo2: %d", xCo3, yCo3);
                    if (quotients.size() == 1) {
                        System.out.println("x>y");
                        results = new int[]{0,z/y};
                        return results;
                    }
                    else{
                        for (int i = 1; i < quotients.size() - 1; i++) {
                            //                        NSLog(@"q = %d", [[quotients objectAtIndex:i]intValue]);
                            //                        NSLog(@"xCo1: %d yCo1: %d", xCo1, yCo1);
                            //                        NSLog(@"xCo2: %d yCo2: %d", xCo2, yCo2);
                            //                        NSLog(@"xCo3: %d yCo3: %d", xCo3, yCo3);
                            //                        NSLog(@"Operation start");
                            xCo2 = xCo3;
                            yCo2 = yCo3;
                            xCo3 *= -quotients.get(i);
                            xCo3 += xCo1;
                            yCo3 *= -quotients.get(i);
                            yCo3 += yCo1;
                            xCo1 = xCo2;
                            yCo1 = yCo2;
                            //                        NSLog(@"Operation complete");
                            //                        NSLog(@"xCo3: %d yCo3: %d", xCo3, yCo3);
                        }
                        System.out.println(xCo3);
                        results = new int[]{xCo3 * multiplier, yCo3 * multiplier};
                    }
                }
                else if (x < y){
                    xCo1 = 1;
                    yCo1 = 0;
                    xCo3 = -1 * quotients.get(0);
                    yCo3 = 1;
                    if (quotients.size() == 1) {
                        System.out.println("x<y");
                        results = new int[]{z/x,0};
                        return results;
                    }
                    else{
                        for (int i = 1; i < quotients.size() - 1; i++) {
                            xCo2 = xCo3;
                            yCo2 = yCo3;
                            xCo3 *= -quotients.get(i);
                            xCo3 += xCo1;
                            yCo3 *= -quotients.get(i);
                            yCo3 += yCo1;
                            xCo1 = xCo2;
                            yCo1 = yCo2;
                        }
                        System.out.println(xCo3);
                        results = new int[]{xCo3 * multiplier, yCo3 * multiplier};
                        return results;
                    }
                }
                else{
                    return null;
                }
            }
        }
        // results = [[NSArray alloc]initWithObjects:[NSNumber numberWithInt:x], [NSNumber numberWithInt:y], nil];
        return results;
    }

    public static int getGCD()
    {
        return gcd;
    }
}
