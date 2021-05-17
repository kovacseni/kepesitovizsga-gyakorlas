package hu.nive.ujratervezes.kepesitovizsga.numberofdigits;

public class NumberOfDigits {

    public int getNumberOfDigits(int number) {
        String numbers = "";
        for (int i = 1; i <= number; i++) {
            numbers = numbers + i;
        }
        return numbers.length();
    }
}
