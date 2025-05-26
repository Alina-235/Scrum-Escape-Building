class Monster extends Character {
    private Kamer kamer;

    public Monster(String naam, String beschrijving, int lives, int characterID, Kamer kamer) {
        super(naam, beschrijving, lives, characterID);
        this.kamer = kamer;
    }

    public void monsterTonen() {
        System.out.println("Het monster " + naam + " bewaakt kamer: " + kamer.getNaam());
    }

    public void valAan(Speler speler) {
        System.out.println("Het monster " + naam + " valt je aan!");
        speler.attacked();
        this.lives--;
        System.out.println(naam + " verliest een leven. Levens over: " + lives);

        if (lives <= 0) {
            this.verslagen = true;
            System.out.println(naam + " is verslagen!");
        }
    }

    public Kamer getKamer() {
        return kamer;
    }

    public boolean isVerslagen() {
        return verslagen;
    }
}

