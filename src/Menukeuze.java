import java.util.Scanner;

interface keuze{
    void menu();
}

class Menukeuze implements keuze{
    static Scanner scanner = new Scanner(System.in);
    static Speler speler = new Speler("", 0);
    static Game game = new Game(speler);

    @Override
    public void menu() {
        int keuze = scanner.nextInt();
        scanner.nextLine();

        switch (keuze) {
            case 1:
                game.storyline();
                game.startGame();
                break;
            case 2:
                break;
            case 3:
                System.out.println("Programma wordt afgesloten.");
                break;
            default:
                System.out.println("Ongeldige keuze, probeer opnieuw.");
                break;
        }
    }
}
