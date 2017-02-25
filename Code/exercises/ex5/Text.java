
package be.leerstad.javabasics.exercises.ex5;


public class Text {

    /*
     * Create a class named Text that: contains a method named occurrencesOfChar,
     * that determines the occurence of a given char in a string. Contains a
     * method named isPalindromic to determine whether or not a string given as
     * parameter is a palindrome. A palindrome is a word that can be read both
     * forwards and backwards. Examples of palindromes are: madam, rotor, level,
     * ...
     */

    public static void main(String[] args) {
        Text text;
        text = new Text();
        System.out.println("must be 2: " + text.occurrencesOfChar("hallo", 'l'));
        System.out.println("must be 1: " + text.occurrencesOfChar("HALLO", 'A'));
        System.out.println("must be 2: " + text.occurrencesOfChar("hallo", 'L'));
        System.out.println("must be 1: " + text.occurrencesOfChar("HALLO", 'a'));
        System.out.println("must be 0: " + text.occurrencesOfChar("hallo", 'b'));
        System.out.println();
        System.out.println("must be true: " + text.isPalindromic("Madam"));
        System.out.println("must be false: " + text.isPalindromic("neger"));
        System.out.println("Must be mAstErpIEcE " + text.specialCase("Masterpiece"));

    }

    public int occurrencesOfChar(String string, char character) {
        int counter = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.toLowerCase().charAt(i) == Character.toLowerCase(character)) {
                counter++;
            }
        }
        return counter;
    }

    /*
     * A new method for the class Text will be a method that takes a string as
     * parameter and returns a modification of this string such that it contains
     * the same string but with chars: as an uppercase for vowels as a lowercase
     * for consonants This will produce the following output for the word
     * "Masterpiece": "MaSTeRPieCe".
     */

    public boolean isPalindromic(String string) {
        return string.equalsIgnoreCase(new StringBuilder(string).reverse().toString());
    }

    private boolean isVowel(char c) {
        return "aeiouy".indexOf(Character.toLowerCase(c)) != -1;

    }

    public String specialCase(String str) {
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); i++) {
            if (isVowel(sb.charAt(i))) {
                sb.setCharAt(i, Character.toUpperCase(sb.charAt(i)));
            } else {
                sb.setCharAt(i, Character.toLowerCase(sb.charAt(i)));
            }
        }
        return sb.toString();

    }


}
