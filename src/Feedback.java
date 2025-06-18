class Feedback implements SpelerObserver{
    @Override
    public void update(Speler speler, GebeurtenisType gebeurtenis){
        switch (gebeurtenis){
            case NIEUWE_KAMER:
                System.out.println("[Feedback] Je bent in een nieuwe kamer");
                break;
            case SPELER_IS_AANGEVALLEN:
                System.out.println("[Feedback] Fout antwoord! Je bent aangevallen!");
                break;
            case NIEUW_MONSTER_VERSCHENEN:
                System.out.println("[Feedback] Er is een nieuwe monster verschenen!");
                break;
            case MONSTER_IS_VERSLAGEN:
                System.out.println("[Feedback] Goed gedaan! Je hebt een monster verslagen. De deur is nu open. ");
                break;

            default:
                System.out.println("[Feedback] " + gebeurtenis);
        }
        speler.toonStatus();
    }
}