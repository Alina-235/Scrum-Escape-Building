public class KeyJoker implements Joker {
    private int usesRemaining = 2;

    @Override
    public void gebruik(Speler speler) {
        if (usesRemaining > 0) {
            System.out.println("Je hebt een sleutel gebruikt om een monster te ontwijken!");
            usesRemaining--;

            if (!speler.getActieveMonsters().isEmpty()) {
                speler.losMonsterOp(speler.getActieveMonsters().get(0));
            }

            System.out.println("Je kunt de KeyJoker nog " + usesRemaining + " keer gebruiken.");
        } else {
            System.out.println("Je hebt geen KeyJoker-gebruiken meer over.");
        }
    }

    @Override
    public boolean beschikbaarIn(Kamer kamer) {
        return usesRemaining > 0;
    }
}
