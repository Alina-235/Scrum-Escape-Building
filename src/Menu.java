public class Menu {
    Menukeuze keuze = new Menukeuze();

    public void MenuInloggen(Speler speler) {
        System.out.println("Login Menu:");
        System.out.println("1. Inloggen");
        System.out.println("2. Afsluiten");
        keuze.MenuInloggenKeuze(speler);
    }

    public void Hoofdmenu(Game game) {
        System.out.println("Hoofdmenu:");
        System.out.println("1. Start spel");
        System.out.println("2. Toon status");
        System.out.println("3. Stoppen");
        keuze.HoofdmenuKeuze(game);
    }
}
