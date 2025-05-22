package ch.bbw.m335;

import java.util.HashMap;
import java.util.Map;

public class MorseCodeTree {
    private Node root;
    private Map<Character, String> morseCodeMap;

    public MorseCodeTree() {
        root = new Node(' '); // Root node is empty
        morseCodeMap = new HashMap<>();
        initializeMorseCodeMap();
        buildTree();
    }

    private void initializeMorseCodeMap() {
        // Based on the provided image
        morseCodeMap.put('A', ".-");
        morseCodeMap.put('B', "-...");
        morseCodeMap.put('C', "-.-.");
        morseCodeMap.put('D', "-..");
        morseCodeMap.put('E', ".");
        morseCodeMap.put('F', "..-.");
        morseCodeMap.put('G', "--.");
        morseCodeMap.put('H', "....");
        morseCodeMap.put('I', "..");
        morseCodeMap.put('J', ".---");
        morseCodeMap.put('K', "-.-");
        morseCodeMap.put('L', ".-..");
        morseCodeMap.put('M', "--");
        morseCodeMap.put('N', "-.");
        morseCodeMap.put('O', "---");
        morseCodeMap.put('P', ".--.");
        morseCodeMap.put('Q', "--.-");
        morseCodeMap.put('R', ".-.");
        morseCodeMap.put('S', "...");
        morseCodeMap.put('T', "-");
        morseCodeMap.put('U', "..-");
        morseCodeMap.put('V', "...-");
        morseCodeMap.put('W', ".--");
        morseCodeMap.put('X', "-..-");
        morseCodeMap.put('Y', "-.--");
        morseCodeMap.put('Z', "--..");
    }

    private void buildTree() {
        for (Map.Entry<Character, String> entry : morseCodeMap.entrySet()) {
            insert(entry.getKey(), entry.getValue());
        }
    }

    private void insert(char character, String morseCode) {
        Node current = root;
        for (char c : morseCode.toCharArray()) {
            if (c == '.') {
                if (current.dot == null) {
                    current.dot = new Node(' '); // Intermediate nodes can be empty
                }
                current = current.dot;
            } else if (c == '-') {
                if (current.dash == null) {
                    current.dash = new Node(' '); // Intermediate nodes can be empty
                }
                current = current.dash;
            }
        }
        current.value = character;
    }

    public String decode(String morseCode) {
        StringBuilder decodedText = new StringBuilder();
        String[] words = morseCode.trim().split(" / "); // Split by word separator
        for (String word : words) {
            String[] letters = word.split(" "); // Split by letter separator
            for (String letter : letters) {
                decodedText.append(decodeLetter(letter));
            }
            decodedText.append(" "); // Add space between words
        }
        return decodedText.toString().trim();
    }

    private char decodeLetter(String morseChar) {
        Node current = root;
        for (char c : morseChar.toCharArray()) {
            if (c == '.') {
                current = current.dot;
            } else if (c == '-') {
                current = current.dash;
            }
            if (current == null) {
                return '?'; // Character not found
            }
        }
        return current.value == ' ' ? '?' : current.value; // If it's an intermediate node without a value, return '?'
    }

    public String encode(String text) {
        StringBuilder morseText = new StringBuilder();
        text = text.toUpperCase();
        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);
            if (character == ' ') {
                if (i > 0 && text.charAt(i - 1) != ' ') { // Add word separator only if previous was not a space
                    morseText.append("/ ");
                }
            } else {
                String morse = morseCodeMap.get(character);
                if (morse != null) {
                    morseText.append(morse).append(" ");
                } else {
                    morseText.append("? "); // Character not in map
                }
            }
        }
        return morseText.toString().trim();
    }
}
