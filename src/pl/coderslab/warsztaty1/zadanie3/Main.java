package pl.coderslab.warsztaty1.zadanie3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        play();
    }

    static int guess(int min, int max) {
        int guess = (max - min) / 2 + min;
        System.out.println("Zgaduje: " + guess);
        return guess;
    }

    static String takeHint() {
        Scanner scan = new Scanner(System.in);
        String hint = scan.nextLine();
        while (!(hint.equals("więcej") || hint.equals("mniej") || hint.equals("trafiłeś"))) {
            System.out.println("Nie rozumiem");
            hint = scan.nextLine();
        }
        return hint;
    }

    static void play() {
        int min = 0;
        int max = 100;
        System.out.println("Pomyśl liczbę od 0 do 1000 a ja ją zgadne.");
        int current = guess(min, max);
        int counter = 1;
        String done = takeHint();
        while (!done.equals("trafiłeś")) {
            if(min == max){
                System.out.println("Oszukujesz!");
                return;
            }
            counter++;
            if (done.equals("mniej")) {
                max = current;
            } else if (done.equals("więcej")) {
                min = current;
            }
            current = guess(min, max);
            done = takeHint();
        }
        System.out.println("Dziękuję. Potrzebowałem "+counter+" prób.");
    }

}


