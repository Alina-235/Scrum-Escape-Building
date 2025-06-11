public class Monster extends Character {
    private String monsterDesign;

    public Monster(String naam, String beschrijving, int characterID, int lives, String monsterDesign) {
        super(naam, beschrijving, characterID, lives);
        this.monsterDesign = monsterDesign;
    }

    public Monster(Monster Monster) {
        super(Monster.naam, Monster.beschrijving, Monster.characterID, Monster.lives);
        this.monsterDesign = Monster.monsterDesign;
    }

    public void verliesLeven() {
        lives--;
        if (lives <= 0) {
            verslagen = true;
            System.out.println(naam + " is verslagen!");
        }
    }

    public void monsterTonen() {
        System.out.println("Een monster verschijnt: " + naam);
        System.out.println(monsterDesign);
        System.out.println(beschrijving);
    }

    public String getMonsterDesign() {
        return monsterDesign;
    }
}

