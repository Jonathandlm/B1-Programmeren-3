
package be.leerstad.javabasics.exercises.ex6;


public class TextStarter {

    public static void main(String[] args) {
        Text text;
        text = new Text();
        System.out.println("must be true: " + text.occurencesOfChar("hallo", 'l'));
        System.out.println("must be true: " + text.occurencesOfChar("HALLO", 'A'));
        System.out.println("must be true: " + text.occurencesOfChar("hallo", 'L'));
        System.out.println("must be true: " + text.occurencesOfChar("HALLO", 'a'));
        System.out.println("must be false: " + text.occurencesOfChar("hallo", 'b'));
        System.out.println();
        System.out.println("must be true: " + text.isPalindromic("Madam"));
        System.out.println("must be false: " + text.isPalindromic("neger"));
        System.out.println(text.specialCase("Masterpiece"));

    }
}
