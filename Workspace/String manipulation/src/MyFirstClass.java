public class MyFirstClass {

    public static void main(String[] args) {
        printHelloWorld();
        System.out.println(concatStrings("I ", "love ", "Java!"));
        printConcatStrings("I ", "love ", "Java!");
        System.out.println(getStringLength("Java"));
        printUppercase("Java");
    }

    private static void printHelloWorld(){
        System.out.println("Hello World");
    }

    private static String concatStrings(String str1, String str2, String str3){
        return str1 + str2 + str3;
    }

    private static void printConcatStrings(String str1, String str2, String str3){
        System.out.println(concatStrings(str1, str2, str3));
    }

    private static int getStringLength(String str){
        return str.length();
    }

    private static void printUppercase(String str){
        System.out.println(str.toUpperCase());
    }
}