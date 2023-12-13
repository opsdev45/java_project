package org.example;
import  java.util.Scanner;
/**
 * Maman 11 - Check who have the min distance between 3 points 
 * 
 * @author Ofir Izchakov
 * @version 13/12/2023
 */
public class Origin {
    public static void main (String [] args)
    {
        // Take three points from the user (x,y)
        Scanner scan = new Scanner (System.in);
        System.out.println("Enter first point coordinates:");
        int x1 = scan.nextInt();
        int y1 = scan.nextInt();
        double dist1 = Math.sqrt(Math.pow(x1, 2) + Math.pow(y1, 2)); // Distance between 0,0 to the user x,y
        System.out.println(dist1);

        System.out.println ("Enter second point coordinates:");
        int x2 = scan.nextInt();
        int y2 = scan.nextInt();
        double dist2 = Math.sqrt(Math.pow(x2, 2) + Math.pow(y2, 2)); // Distance between 0,0 to the user x,y


        System.out.println ("Enter third point coordinates:");
        int x3 = scan.nextInt();
        int y3 = scan.nextInt();
        double dist3 = Math.sqrt(Math.pow(x3, 2) + Math.pow(y3, 2)); // Distance between 0,0 to the user x,y

        // Check the min distance between all the three distance
        double result1 = Math.min(dist1,dist2);
        double finalResult = Math.min(result1,dist3);

        if (dist1 == finalResult ){
            System.out.println("The nearest point to the origin is " + "(" + x1 +","+ y1 +")");
        }
        else if (dist2 == finalResult) {
            System.out.println("The nearest point to the origin is " + "(" + x2 +","+ y2 +")");
        }
        else {
            System.out.println("The nearest point to the origin is " + "(" + x3 +","+ y3 +")");
        }

    } 
}  
