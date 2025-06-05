class Menu {
    static Menukeuze keuze = new Menukeuze();

    public void mainMenu() {
            System.out.println("1. Start Game");
            System.out.println("2. Log in");
            System.out.println("3. Exit");
            System.out.println();
            System.out.print("Maak uw keuze: ");
            keuze.menu();
    }
}

