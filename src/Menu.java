public class Menu {
    private GameController controller;

    public Menu(GameController controller) {
        this.controller = controller;
    }

    public void start() {
        boolean doorgaan = true;

        while (doorgaan && !controller.isGameOver()) {
            int keuze = Menukeuze.toonMenuEnKrijgKeuze();
            doorgaan = controller.verwerkKeuze(keuze);
        }
    }
}
