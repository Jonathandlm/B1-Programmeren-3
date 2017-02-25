public class Text {
    public static void main(String[] args) {
        System.out.format("Aantal \'a\' in \'Java\': %d%n", occurrencesOfChar('a',"Java"));
        System.out.format("Is \'meetsysteem\' een palindroom? %s%n",isPalindromic("meetsysteem"));
        System.out.format("Is \'a\' een klinker? %s%n",isVowel('a'));
        System.out.println(modifyString("Masterpiece"));
    }

    private static int occurrencesOfChar(char c, String str){
        return str.length() - str.replace(String.valueOf(c), "").length();
    }

    private static boolean isPalindromic(String str){
        StringBuilder sb = new StringBuilder(str);
        return str.equals(sb.reverse().toString());
    }
    
    private static String modifyString(String str){
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (isVowel(c)) result += String.valueOf(c).toLowerCase();
            else result += String.valueOf(c).toUpperCase();
        }
        return result;
    }
    
    private static boolean isVowel(char c){
        switch (c){
            case 'a':
            case 'e':
            case 'i':
            case 'o':
            case 'u':
                return true;
            default:
                return false;
        }
    }
    
    
}
