
package be.leerstad.javabasics.exercises.ex5;


public class TextStarter {

    public static void main(String[] args) {
        Text text;
        text = new Text();
        System.out.println("must be true: " + text.occurrencesOfChar("hallo", 'l'));
        System.out.println("must be true: " + text.occurrencesOfChar("HALLO", 'A'));
        System.out.println("must be true: " + text.occurrencesOfChar("hallo", 'L'));
        System.out.println("must be true: " + text.occurrencesOfChar("HALLO", 'a'));
        System.out.println("must be false: " + text.occurrencesOfChar("hallo", 'b'));
        System.out.println();
        System.out.println("must be true: " + text.isPalindromic("Madam"));
        System.out.println("must be false: " + text.isPalindromic("neger"));
    }
}
