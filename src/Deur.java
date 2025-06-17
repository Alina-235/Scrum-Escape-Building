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
                    System.out.println("[Boek] " + volgendeKamer.getBoek());
                }

                break;

            case "Speler is verslagen":
                open = false;
                System.out.println("[Deur] De deur blijft dicht. Versla de monster eerst.");
                break;
        }
    }

    private Kamer bepaalVolgendeKamer(Kamer huidige) {
        String naam = huidige.getNaam();

        if (huidige.getNaam().equalsIgnoreCase("DailyScrum")) {
            return new KamerPlanning("Planning", "Sprint KamerPlanning", "Plan de sprint", 2,"In de Sprint Planning wordt bepaald wat het team in de komende sprint gaat doen. Focus op het kiezen van de juiste taken en het stellen van doelen.");
        }
        if (huidige.getNaam().equalsIgnoreCase("Planning")) {
            return new KamerRetrospective("Retrospective", "Sprint KamerRetrospective", "Reflecteer en verbeter", 3, "De Sprint Retrospective is een moment van reflectie: wat ging goed, wat kan beter? Eerlijkheid helpt het team groeien.");
        }
        if (huidige.getNaam().equalsIgnoreCase("=Retrospective")) {
            return new KamerReview("Review", "Sprint KamerReview", "Bekijk het werk", 4,"Tijdens de Sprint Review presenteert het team wat er is opgeleverd. De stakeholders geven feedback. Samenwerken en openheid zijn hier belangrijk.");
        }
        if (huidige.getNaam().equalsIgnoreCase("Review")) {
            return new KamerScrumboard("Scrumboard", "Sprint KamerScrumboard", "Overzicht van taken", 5,"Het Scrumboard geeft overzicht: wat is To Do, In Progress, Done? Houd overzicht en werk samen om doelen te bereiken.");
        }
        if (huidige.getNaam().equalsIgnoreCase("Scrumboard")) {
            return new KamerDailyScrum("DailyScrum","Sprint KamerDailyscrum", "houd een standup", 1, "De Daily Scrum is een kort overleg van 15 minuten waarin het team de voortgang bespreekt. Denk aan: wat heb je gedaan, wat ga je doen.");
        }

        return null;
    }


    public boolean isOpen(){
        return open;
    }
}
