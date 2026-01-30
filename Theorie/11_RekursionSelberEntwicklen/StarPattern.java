/**
 * Doppelte Rekursion
 *
 * @author Daniel Fahrni
 * @version 14.03.2025
 */
public class StarPattern {
    public static void main(String[] args) {
        printPatternIterativ(5);
    }

    static void printPatternIterativ(int n) {
        for (int i = 1; i <= n; i++) { // Äussere Schleife für die Zeilen
            for (int j = 1; j <= i; j++) { // Innere Schleife für die Sterne
                System.out.print("*");
            }
            System.out.println(); // Neue Zeile nach jeder Reihe von Sternen
        }
    }
}