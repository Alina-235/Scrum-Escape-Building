class Menu {
    static Menukeuze keuze = new Menukeuze();

    public static void mainMenu(String naam) {
        System.out.println("\n--- Scrum Escape Building ---");
        System.out.println("1. Start Game");
        System.out.println("2. Log in");
        System.out.println("3. Exit");
        keuze.menu(naam);
    }
}
