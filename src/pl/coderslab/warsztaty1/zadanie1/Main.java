package pl.coderslab.warsztaty1.zadanie1;

import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        play();
    }

    static int generateRand() {
        Random rand = new Random();
        return rand.nextInt(100) + 1;
    }

    static int takeInt() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Zgadnij liczbę");
        boolean isNumber = false;
        int number = 0;
        while (!isNumber) {
            String next = scan.nextLine();
            try {
                number = Integer.parseInt(next);
                isNumber = true;
            } catch (NumberFormatException e) {
                System.out.println("To nie jest liczba!");
            }
        }
        return number;
    }

    static boolean compare(int number1, int number2) {
        if (number1 < number2) {
            System.out.println("Za mało!");
            return false;
        } else if (number1 > number2) {
            System.out.println("Za dużo!");
            return false;
        } else {
            return true;
        }
    }

    static void play() {
        int secret = generateRand();
        int number = takeInt();
        while (!compare(number, secret)) {
            number = takeInt();
        }
        System.out.println("Zgadłeś!");
    }
}
