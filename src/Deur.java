public class Deur implements SpelerObserver {
    private boolean open = false;

    @Override
    public void update(Speler speler, String gebeurtenis) {
        switch (gebeurtenis) {
            case "Monster is verslagen":
                open = true;
                System.out.println("[Deur] De deur is open. Je kunt doorgaan naar de volgende kamer.");
                Kamer volgendeKamer = bepaalVolgendeKamer(speler.getHuidigeKamer());
                if (volgendeKamer != null) {
                    speler.moveTo(volgendeKamer);
                }

                break;

            case "Speler is verslagen":
                open = false;
                System.out.println("[Deur] De deur blijft dicht. Versla de monster eerst.");
                break;
        }
    }

    private Kamer bepaalVolgendeKamer(Kamer huidige) {
        if (huidige.getNaam().equalsIgnoreCase("KamerDailyScrum")) {
            return new KamerPlanning("Planning", "Sprint KamerPlanning", "Plan de sprint", 2);
        }
        if (huidige.getNaam().equalsIgnoreCase("KamerPlanning")) {
            return new KamerRetrospective("Retrospective", "Sprint KamerRetrospective", "Reflecteer en verbeter", 3);
        }
        if (huidige.getNaam().equalsIgnoreCase("KamerRetrospective")) {
            return new KamerReview("Review", "Sprint KamerReview", "Bekijk het werk", 4);
        }
        if (huidige.getNaam().equalsIgnoreCase("KamerReview")) {
            return new KamerScrumboard("Scrumboard", "Sprint KamerScrumboard", "Overzicht van taken", 5);
        }
        if (huidige.getNaam().equalsIgnoreCase("KamerScrumboard")) {
            return null;
        }

        return null;
    }


    public boolean isOpen(){
        return open;
    }
}

