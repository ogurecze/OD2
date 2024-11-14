import java.util.Scanner;

class CaesarCipher {

    public static String encrypt(String text, int shift) {
        StringBuilder encryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                character = (char) ((character - base + shift) % 26 + base);
            }
            encryptedText.append(character);
        }

        return encryptedText.toString();
    }

    public static String decrypt(String text, int shift) {
        return encrypt(text, 26 - shift % 26);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Wpisz tekst do zaszyfrowania/deszyfrowania: ");
        String text = scanner.nextLine();

        System.out.print("Wpisz klucz (liczba przesunięcia): ");
        int shift = scanner.nextInt();

        String encryptedText = encrypt(text, shift);
        String decryptedText = decrypt(encryptedText, shift);

        System.out.println("Zaszyfrowany tekst: " + encryptedText);
        System.out.println("Odszyfrowany tekst: " + decryptedText);

        scanner.close();
    }
}
