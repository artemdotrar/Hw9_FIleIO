import java.io.*;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        createPalindromeFile("file1.txt", "file1.OnlyPalindromes.txt");
        searchShortSentenceOrPalindrome("file2.txt");
        if (!isHaveBadWords("file3.txt")) {
            System.out.println("Текст прошел проверку");
        }


    }

    //1)В исходном файле находятся слова, каждое слово на новой стороке. После запуска программы должен создать файл,
    // который будет содержать в себе только полиндромы
    public static void createPalindromeFile(String fileToChekPath, String fileToCreatePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileToChekPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(fileToCreatePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (TextFormatter.isPalindrome(line)) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //2)Текстовый файл содержит текст. После запуска программы в другой файл должны записаться только те предложения в которых от 3-х до 5-ти слов.
    // Если в предложении присутствует слово-палиндром, то не имеет значения какое кол-во слов в предложении, оно попадает в новый файл.
    public static void searchShortSentenceOrPalindrome(String filePath) {
        String[] sentences = readFile(filePath).split("\\.");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("file2.ShortsAndPalindromes.txt"))) {
            for (int i = 0; i < sentences.length; i++) {
                sentences[i] = sentences[i].trim();
                if (TextFormatter.isHavePalindrome(sentences[i])) {
                    bw.write(sentences[i] + ".\n");
                } else if (TextFormatter.getNumberOfWords(sentences[i]) >= 3 && TextFormatter.getNumberOfWords(sentences[i]) <= 5) {
                    bw.write(sentences[i] + ".\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //3)Проверка на цензуру
    public static boolean isHaveBadWords(String filePath) {
        boolean isHaveBadWords = false;
        String[] sentences = readFile(filePath).split("\\.");
        for (String sentence : sentences) {
            for (String word : TextFormatter.deletePunctuationMarks(sentence).split(" ")) {
                if (isBadWord(word)) {
                    System.out.println("Предложение не прошло цензуру: " + sentence + "\t-Содержит запрещенное слово " + word);
                    isHaveBadWords = true;
                }
            }
        }
        return isHaveBadWords;
    }

    private static boolean isBadWord(String word) {
        String[] badWords = readFile("BlackList.txt").split(";");
        for (String badWord : badWords) {
            if (TextFormatter.deletePunctuationMarks(word).trim().toLowerCase().equals(badWord.toLowerCase())) {
                return true;
            }
        }
        return false;

    }

    //считывание файла в String
    private static String readFile(String path) {
        String text = "";
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                text += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return text;
    }

}
