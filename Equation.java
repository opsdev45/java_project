package org.example;

import java.util.Scanner;

public class Equation {
    public static void main(String[] args) {
        //Take three number a,b,c from the user for 𝑎𝑥^2 + bx + 𝑐 = 0
        Scanner scan = new Scanner(System.in);
        System.out.print("Enter 3 coefficients of the polynomial equation: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        int c = scan.nextInt();

        // Calculate the discriminant (the value inside the square root)
        double rootValue = Math.pow(b, 2) - (4 * a * c);
        System.out.println(rootValue);
        // Check if 'a' not zero
        if (a != 0) {
            if (rootValue == 0) {
                // Calculate and print the solution when the discriminant is zero
                double finalResult = (-b / 2.0 * a);
                System.out.println("There is 1 solution. X1 = " + finalResult);
            }
            else if (rootValue > 0) {
                // Calculate and print the two solutions when the discriminant is positive
                double plusResult = (-b + rootValue / 2.0 * a);
                double minosResult = (-b - rootValue / 2.0 * a);
                System.out.println("There are 2 solutions. X1= " + plusResult + " X2=  " + minosResult);
            }
            else {
                // The discriminant is negative, indicating no real solutions
                System.out.println("There is no solution.");
            }
        }
    }

}
