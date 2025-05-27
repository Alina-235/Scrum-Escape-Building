public class Menu {

    public void mainMenu() {
        while (true) {  
            System.out.println("1. Start Game");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Maak uw keuze: ");
            while (true) {
                int keuze = Menukeuze.toonMenuEnKrijgKeuze();
                if (!GameController.verwerkKeuze(keuze)) {
                    break;
                }
            }

        }
    }
}

