package com.vivek;


import java.util.*;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the no. of subjects :");
        int num = sc.nextInt();

        String[] subjects = new String[num]; //Array to store names of various subjects

        for(int i=0;i<num;i++){
            System.out.print("Enter name of subject no."+(i+1)+" :");
            subjects[i] =sc.nextLine();
            sc.nextLine();

        }
        float[] marks = new float[num]; //Array to store marks of various subjects

        for(int i=0;i<num;i++){
            System.out.println("Enter marks in "+subjects[i]+" (out of 100) :");
            marks[i] = sc.nextFloat();
        }

        sc.close();

        float total_marks=0; // Total marks of all subjects.

        for(float mark : marks){
            total_marks += mark;
        }

        float avg_per = total_marks/num;

        char Grade = 'F';

        if(avg_per >= 90) { Grade = 'A'; }

        else if (avg_per >= 75) { Grade = 'B' ;}

        else if (avg_per >= 60) { Grade = 'C';}

        else if (avg_per >= 50) { Grade = 'D';}

        else if (avg_per >= 45) { Grade = 'E';}

        System.out.println("Total marks obtained in all subjects :"+total_marks+"/"+(100*num));
        System.out.println("Average percentage :"+avg_per+"%");
        System.out.println("Grade :"+Grade);

    }
}
