public class Logger implements SpelerObserver {
    @Override
    public void update(Speler speler, String gebeurtenis) {
        String kamerNaam;
        if (speler.getHuidigeKamer() != null) {
            kamerNaam = speler.getHuidigeKamer().getNaam();
        } else {
            kamerNaam = "Geen";
        }

        System.out.println("[LOG] Gebeurtenis:" + gebeurtenis + " | Speler: " + speler.getNaam() + " | Levens: " + speler.getLives() + " | Kamer: " + kamerNaam);
    }
}
