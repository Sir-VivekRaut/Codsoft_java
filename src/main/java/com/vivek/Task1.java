package com.vivek;

import java.util.*;
public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("...$WELCOME TO THE NUMBER GAME$.....by-Vivek");
        System.out.println("Enter 'S' to start the game. Type 'exit' to quit.");

        while (true) {
            String input = sc.nextLine();

            if (input.equals("S")||input.equals("s")) {
                play();
            } else if (input.equalsIgnoreCase("exit")) {
                System.out.println("Exiting program.");
                break;
            } else {
                System.out.println("Enter 'S' to start the game. Type 'exit' to quit.");
            }
        }
        sc.close();
    }
    static void play(){
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.println("\nStarting the game..\n");

        int a, guess, start =5, c = 1;
        boolean flag = false , play = true;

        while(play) {
            a = r.nextInt(100);
            while (start > 0) {
                System.out.println("Enter your Guess (chances:"+start+") :");
                guess = sc.nextInt();
                if (guess == a) {
                    flag = true;
                    break;
                }
                else {
                    start--;
                }
            }

            if (flag) {
                System.out.println("Congratulations! You won the game.");
            } else {
                System.out.println("The number was " + a + "\n Better luck next time.\n");
            }
            System.out.println("Do you wanna play again ?(Y/N)");
            String b = sc.next();
            if(Objects.equals(b, "N") || Objects.equals(b, "n")){
                play = false;
                break;
            }
            else {play();}
        }
        System.out.println("Have a Good day!!");
        return;
    }
}
