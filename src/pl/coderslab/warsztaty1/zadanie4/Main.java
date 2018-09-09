package pl.coderslab.warsztaty1.zadanie4;

import org.apache.commons.lang3.StringUtils;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        play();
    }

    private static boolean checkParams() {
        try {
            Integer[] params = retrieveParams();
            int sum = sumOfThrows(params[0],params[1]) + params[2];
            System.out.println("Po uwzględnieniu modyfikatora: "+sum);
            return true;
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Błędny kod rzutu");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Błędny kod rzutu");
            return false;
        }
    }

    private static int sumOfThrows(int throwsNumber, int diceType){
        int sum = 0;
        System.out.println("Wyrzucono:");
        for(int i=0; i<throwsNumber; i++){
            int number = generateRand(diceType);
            System.out.println(number);
            sum += number;
        }
        return sum;
    }

    private static int generateRand(int diceType) {
        Random rand = new Random();
        return rand.nextInt(diceType) + 1;
    }

    private static Integer[] retrieveParams() throws InvalidParameterException, ArrayIndexOutOfBoundsException, NumberFormatException {
        Integer[] parameters = new Integer[3];
        Scanner scan = new Scanner(System.in);
        System.out.println("Podaj kod rzutu");
        String code = scan.nextLine();
        code = StringUtils.lowerCase(code);
        String[] params = code.split("d");
        parameters[0] = Integer.parseInt(params[0]);
        if (params[1].contains("+")) {
            parameters[1] = Integer.parseInt(params[1].split("\\+")[0]);
            parameters[2] = Integer.parseInt(params[1].split("\\+")[1]);
        } else if (params[1].contains("-")) {
            parameters[1] = Integer.parseInt(params[1].split("-")[0]);
            parameters[2] = Integer.parseInt("-" + params[1].split("-")[1]);
        } else {
            parameters[1] = Integer.parseInt(params[1]);
        }

        checkDice(parameters[1]);
        return parameters;

    }

    private static void checkDice(int diceType) throws InvalidParameterException {
        Integer[] allowedDices = {3, 4, 6, 8, 10, 12, 20, 100};
        if (Arrays.asList(allowedDices).contains(diceType)) {

        } else {
            throw new InvalidParameterException("Błędny rodzaj kostki");
        }
    }

    private static void play() {
        while (!checkParams()) {

        }
    }
}
