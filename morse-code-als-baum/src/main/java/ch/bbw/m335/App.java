package ch.bbw.m335;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        MorseCodeTree morseCodeTree = new MorseCodeTree();

        // Encode example
        String textToEncode = "HELLO WORLD";
        String encodedText = morseCodeTree.encode(textToEncode);
        System.out.println("Encoded \"" + textToEncode + "\": " + encodedText);

        // Decode example
        String morseToDecode = ".... . .-.. .-.. --- / .-- --- .-. .-.. -..";
        String decodedText = morseCodeTree.decode(morseToDecode);
        System.out.println("Decoded \"" + morseToDecode + "\": " + decodedText);

        // Example from the image
        String textToEncodeImage = "SOS";
        String encodedTextIamge = morseCodeTree.encode(textToEncodeImage);
        System.out.println("Encoded \"" + textToEncodeImage + "\": " + encodedTextIamge);

        String morseToDecodeImage = "... --- ...";
        String decodedTextImage = morseCodeTree.decode(morseToDecodeImage);
        System.out.println("Decoded \"" + morseToDecodeImage + "\": " + decodedTextImage);
    }
}
