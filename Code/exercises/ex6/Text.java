package be.leerstad.javabasics.exercises.ex6;


public class Text {

    /*
     * Create a class named Text that: contains a method named occurencesOfChar,
     * that determines the occurence of a given char in a string. contains a
     * method named isPalindromic to determine whether or not a string given as
     * parameter is a palindrome. A palindrome is a word that can be read both
     * forwards and backwards. Examples of palindromes are: madam, rotor, level,
     * ...
     */

    public boolean occurencesOfChar(String string, char character) {
        return string.toLowerCase().indexOf(Character.toLowerCase(character)) != -1;
    }

    public boolean isPalindromic(String string) {
        return string.equalsIgnoreCase(new StringBuilder(string).reverse().toString());
    }

    /*
     * A new method for the class Text will be a method that takes a string as
     * parameter and returns a modification of this string such that it contains
     * the same string but with chars: as an uppercase for vowels as a lowercase
     * for consonants This will produce the following output for the word
     * �Masterpiece�: "MaSTeRPieCe".
     */

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
