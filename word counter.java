import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCounter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("write 'text' to enter text or 'file' to provide a file: ");
        String inputType = scanner.nextLine();

        String text = "";
        if (inputType.equalsIgnoreCase("text")) {
            System.out.print("Enter the text: ");
            text = scanner.nextLine();
        } else if (inputType.equalsIgnoreCase("file")) {
            System.out.print("Enter the file path: ");
            String filePath = scanner.nextLine();
            try {
                text = readFile(filePath);
            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                return;
            }
        } else {
            System.out.println("Invalid input!");
            return;
        }

        int wordCount = countWords(text);
        System.out.println("Word count: " + wordCount);
    }

    public static int countWords(String text) {
        String[] words = text.trim().split("[\\p{Punct}\\s]+");
        return words.length;
    }

    public static String readFile(String filePath) throws FileNotFoundException {
        StringBuilder content = new StringBuilder();
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            content.append(scanner.nextLine()).append("\n");
        }
        scanner.close();
        return content.toString();
    }
}