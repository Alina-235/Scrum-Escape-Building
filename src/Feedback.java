public class Feedback implements SpelerObserver{
    @Override
    public void update(Speler speler, String gebeurtenis){
        switch (gebeurtenis){
            case "Nieuwe kamer":
                System.out.println("[Feedback] Je bent in een nieuwe kamer");
                break;
            case "Speler is aangevallen":
                System.out.println("[Feedback] Fout antwoord! Je bent aangevallen");
                break;
            case "Nieuwe monster verschenen":
                System.out.println("[Feedback] Er is een nieuwe monster verschenen");
                break;
            case "Monster verslagen":
                System.out.println("[Feedback] Goed gedaan! Je hebt een monster verslagen. De deur is nu open. ");
                break;

            default:
                System.out.println("[Feedback] " + gebeurtenis);
        }
        speler.toonStatus();
}
}
