/**
 * Palindrom Beispiel
 *
 * @author Daniel Fahrni
 * @author https://www.delftstack.com/de/howto/java/check-if-string-is-palindrome-in-java/
 * @version 31.01.202
 */
public class RekursionAusprobieren {
    static boolean isPalindrom(String s) {
        // Abbruchbedingung
        // TODO
        // rekursiver Aufruf
        // TODO
        return true;
    }

    public static void main(String[] args) {
        String s = "ava";
        s = s.toLowerCase();
        if (isPalindrom(s))
            System.out.print("Yes, it is a palindrome.");
        else
            System.out.print("No, it is not a palindrome.");
    }
}
