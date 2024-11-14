import java.util.*;

class CaesarBreaker {

    private static final char[] POLISH_LETTER_FREQUENCY = {'a', 'e', 'o', 'i', 'z', 'n', 's', 'r', 'w', 'c', 'y', 'd', 'k', 'p', 'm', 'u', 'j', 'l', 't', 'b', 'g', 'h', 'f', 'v', 'q', 'x'};

    public static String decrypt(String text, int shift) {
        StringBuilder decryptedText = new StringBuilder();

        for (char character : text.toCharArray()) {
            if (Character.isLetter(character)) {
                char base = Character.isUpperCase(character) ? 'A' : 'a';
                character = (char) ((character - base - shift + 26) % 26 + base);
            }
            decryptedText.append(character);
        }

        return decryptedText.toString();
    }

    public static List<String> crackCaesarCipher(String encryptedText, int numResults) {
        Map<Character, Integer> frequencyMap = new HashMap<>();

        for (char character : encryptedText.toLowerCase().toCharArray()) {
            if (Character.isLetter(character)) {
                frequencyMap.put(character, frequencyMap.getOrDefault(character, 0) + 1);
            }
        }

        List<Character> sortedChars = new ArrayList<>(frequencyMap.keySet());
        sortedChars.sort((a, b) -> frequencyMap.get(b) - frequencyMap.get(a));

        List<String> possibleDecryptions = new ArrayList<>();

        for (int i = 0; i < Math.min(numResults, 26); i++) {
            char mostFrequentChar = sortedChars.get(0);
            int shift = (mostFrequentChar - POLISH_LETTER_FREQUENCY[i] + 26) % 26;
            String decryptedText = decrypt(encryptedText, shift);
            possibleDecryptions.add("Przesunięcie: " + shift + " - " + decryptedText);
        }

        return possibleDecryptions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Podaj zaszyfrowany tekst: ");
        String encryptedText = scanner.nextLine();

        System.out.print("Ile najbardziej prawdopodobnych wyników chcesz zobaczyć (do 10): ");
        int numResults = scanner.nextInt();
        numResults = Math.min(numResults, 10);

        List<String> results = crackCaesarCipher(encryptedText, numResults);

        System.out.println("Najbardziej prawdopodobne odszyfrowane teksty:");
        for (String result : results) {
            System.out.println(result);
        }

        scanner.close();
    }
}
