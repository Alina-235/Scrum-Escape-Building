import java.util.Scanner;

public class Menukeuze {
    private static final Scanner scanner = new Scanner(System.in);

    public static int toonMenuEnKrijgKeuze() {
        System.out.println("\nMaak een keuze:");
        System.out.println("1. Start spel");
        System.out.println("2. Credits");
        System.out.println("3. Afsluiten");
        System.out.print("Keuze: ");
        int keuze = scanner.nextInt();
        scanner.nextLine();
        return keuze;
    }
}


