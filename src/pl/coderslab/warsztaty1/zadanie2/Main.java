package pl.coderslab.warsztaty1.zadanie2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        play();

    }

    static int takeInt() {
        Scanner scan = new Scanner(System.in);
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

    static int[] takeNumbers() {
        int[] numbers = new int[6];
        System.out.println("Podaj 6 liczb.");
        for (int i = 0; i < numbers.length; i++) {
            int number = takeInt();
            while (!checkInt(numbers, number)) {
                number = takeInt();
            }
            numbers[i] = number;

        }
        Arrays.sort(numbers);
        return numbers;
    }

    static boolean checkInt(int[] numbers, int number) {
        if (number < 1 || number > 49) {
            System.out.println("Podaj liczbe z zakresu [1,49]");
            return false;
        } else if (IntStream.of(numbers).anyMatch(n -> n == number)) {
            System.out.println("Podałeś już taką liczbę!");
            return false;
        } else {
            return true;
        }
    }

    static int[] generateSix() {
        int[] allNumbers = new int[6];
        for (int i = 0; i < allNumbers.length; i++) {
            int number = generateRand();
            while (alreadyGenerated(allNumbers, number)) {
                number = generateRand();
            }
            allNumbers[i] = number;
        }
        Arrays.sort(allNumbers);
        return allNumbers;
    }

    static boolean alreadyGenerated(int[] numbers, int number) {
        if (IntStream.of(numbers).anyMatch(n -> n == number)) {
            return true;
        } else {
            return false;
        }
    }

    static int generateRand() {
        Random rand = new Random();
        return rand.nextInt(49) + 1;
    }

    static int countMatches(int[] firstTab, int[] secondTab) {
        int counter = 0;
        for (int i = 0; i < secondTab.length; i++) {
            if (alreadyGenerated(secondTab, firstTab[i])) {
                counter++;
            }
        }
        return counter;
    }

    static void printResult(int counter) {
        if (counter == 6) {
            System.out.println("Trafiłeś szóstke!");
        } else if (counter == 5) {
            System.out.println("Trafiłeś piątke!");
        } else if (counter == 4) {
            System.out.println("Trafiłeś czwórke!");
        } else if (counter == 3) {
            System.out.println("Trafiłeś trójke!");
        } else if (counter > 0 && counter < 3) {
            System.out.println("Niestety trafiłeś tylko " + counter);
        } else {
            System.out.println("Niestety nic nie trafiłeś.");
        }
    }

    static void play() {
        int[] myNumbers = takeNumbers();
        int[] generatedNumber = generateSix();
        int matches = countMatches(myNumbers, generatedNumber);
        System.out.println("Twoje liczby to " + Arrays.toString(myNumbers));
        System.out.println("Wylosowane liczby to " + Arrays.toString(generatedNumber));
        printResult(matches);
    }
}
