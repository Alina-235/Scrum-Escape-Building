class MonsterAanval {
    public void voerAanval(Monster monster, Speler speler) {
        System.out.println("Het monster " + monster.getNaam() + " valt je aan!");
        speler.attacked();
        monster.verliesLeven();
    }
}