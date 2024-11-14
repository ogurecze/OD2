import java.util.Scanner;

class VigenereCipher {

    private static String generateKey(String text, String key) {
        StringBuilder keyBuilder = new StringBuilder(key);
        while (keyBuilder.length() < text.length()) {
            keyBuilder.append(key);
        }
        return keyBuilder.substring(0, text.length());
    }

    public static String encrypt(String text, String key) {
        StringBuilder encryptedText = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char charText = text.charAt(i);
            char charKey = key.charAt(i);

            if (Character.isLetter(charText)) {
                char base = Character.isUpperCase(charText) ? 'A' : 'a';
                char encryptedChar = (char) ((charText + charKey - 2 * base) % 26 + base);
                encryptedText.append(encryptedChar);
            } else {
                encryptedText.append(charText);
            }
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder decryptedText = new StringBuilder();
        key = generateKey(text, key);

        for (int i = 0; i < text.length(); i++) {
            char charText = text.charAt(i);
            char charKey = key.charAt(i);

            if (Character.isLetter(charText)) {
                char base = Character.isUpperCase(charText) ? 'A' : 'a';
                char decryptedChar = (char) ((charText - charKey + 26) % 26 + base);
                decryptedText.append(decryptedChar);
            } else {
                decryptedText.append(charText);
            }
        }

        return decryptedText.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wpisz tekst do zaszyfrowania/deszyfrowania: ");
        String text = scanner.nextLine();

        System.out.print("Wpisz klucz (słowo lub fraza): ");
        String key = scanner.nextLine();

        String encryptedText = encrypt(text, key);
        String decryptedText = decrypt(encryptedText, key);

        System.out.println("Zaszyfrowany tekst: " + encryptedText);
        System.out.println("Odszyfrowany tekst: " + decryptedText);

        scanner.close();
    }
}
