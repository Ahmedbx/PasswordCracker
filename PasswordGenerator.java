import java.awt.*;
import java.awt.datatransfer.*;
import java.util.Scanner;

public class PasswordGenerator {
    private static int passwordCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the letters for the password: ");
        String letters = scanner.nextLine();

        System.out.print("Enter the numbers for the password: ");
        String numbers = scanner.nextLine();

        System.out.print("Enter the symbols for the password: ");
        String symbols = scanner.nextLine();

        int length;
        do {
            System.out.print("Enter the length of the password : ");
            length = scanner.nextInt();
        } while (length <= 0);

        String generatedPasswords = generatePasswords(letters, numbers, symbols, length);

        // Copy to clipboard
        copyToClipboard(generatedPasswords);

        System.out.println("Generated Passwords:\n" + generatedPasswords);
        System.out.println("Passwords copied to clipboard. You can paste them wherever needed.");
        System.out.println("Total passwords generated: " + passwordCount);
    }

    private static String generatePasswords(String letters, String numbers, String symbols, int length) {
        StringBuilder generatedPasswords = new StringBuilder();

        if (length <= 0) {
            System.out.println("Password length should be greater than 0.");
            return generatedPasswords.toString();
        }

        generatePasswordsHelper(letters, numbers, symbols, length, "", generatedPasswords);

        return generatedPasswords.toString();
    }

    private static void generatePasswordsHelper(String letters, String numbers, String symbols, int length, String currentPassword, StringBuilder generatedPasswords) {
        if (length == 0) {
            generatedPasswords.append(currentPassword).append("\n");
            passwordCount++;
            return;
        }

        for (int i = 0; i < letters.length(); i++) {
            generatePasswordsHelper(letters, numbers, symbols, length - 1, currentPassword + letters.charAt(i), generatedPasswords);
        }

        for (int j = 0; j < numbers.length(); j++) {
            generatePasswordsHelper(letters, numbers, symbols, length - 1, currentPassword + numbers.charAt(j), generatedPasswords);
        }

        for (int k = 0; k < symbols.length(); k++) {
            generatePasswordsHelper(letters, numbers, symbols, length - 1, currentPassword + symbols.charAt(k), generatedPasswords);
        }
    }

    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, selection);
    }
}
