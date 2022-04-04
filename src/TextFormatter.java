public class TextFormatter {

    //2.1. Метод принимает строку и возвращает кол-во слов в строке.
    public static int getNumberOfWords(String str) {
        return deletePunctuationMarks(str).trim().split(" ").length;
    }

    //2.2. Метод принимает строку и проверяет есть ли в строке слово-палиндром.
    public static boolean isHavePalindrome(String str) {
        str = deletePunctuationMarks(str);
        String[] words = str.split(" ");
        boolean isHavePalindrome = false;
        for (int i = 0; i < words.length; i++) {
            if (isPalindrome(words[i])) {
                isHavePalindrome = true;
            }
        }
        return isHavePalindrome;
    }

    //является ли слово палиндромом
    public static boolean isPalindrome(String str) {
        if (str.length() < 3) {
            return false;
        }
        String strReverse = "";
        str = str.toLowerCase();
        for (int i = str.length() - 1; i >= 0; i--) {
            strReverse += str.charAt(i);
        }
        return str.equals(strReverse) ? true : false;
    }

    //возвращает строку без знаков препинания
    public static String deletePunctuationMarks(String str) {
        String resultStr = "";
        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (current != '.' && current != ',' && current != '?' && current != '!' && current != ':') {
                resultStr += current;
            }
        }
        return resultStr;
    }
}
