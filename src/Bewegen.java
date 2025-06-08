import java.util.Scanner;

interface move {
    void bewegen();
}

class Bewegen implements move {
    private Speler speler;
    static Scanner scanner = new Scanner(System.in);
    databaseSelect select = new databaseSelect();

    public Bewegen(Speler speler) {
        this.speler = speler;
    }

    @Override
    public void bewegen() {
        Kamer huidigeKamer = speler.getHuidigeKamer();
        int kamerId = (huidigeKamer != null) ? huidigeKamer.getKamerId() : 1;

        while (true) {
            Kamer kamer = select.getKamerById(kamerId);
            if (kamer == null) {
                System.out.println("Geen kamer gevonden met ID: " + kamerId);
                System.out.println("Beweging gestopt vanwege ongeldig kamer ID.");
                break;
            }

            speler.setHuidigeKamer(kamer);
            System.out.println("Je bent nu in kamer: " + kamer.getNaam());

            System.out.println("Beweeg tussen kamers met a/d (of x om te stoppen):");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("a")) {
                kamerId--;
            } else if (input.equalsIgnoreCase("d")) {
                kamerId++;
            } else if (input.equalsIgnoreCase("x")) {
                System.out.println("Beweging gestopt.");
                break;
            } else {
                System.out.println("Ongeldige invoer.");
            }
        }
    }
}
