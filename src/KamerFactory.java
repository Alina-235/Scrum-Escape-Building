public class KamerFactory {
    public static Kamer createKamer(int kamerId, String naam, String beschrijving, String type, String doel, String boek) {
        switch (type.toLowerCase()) {
            case "start":
                return new KamerStart(naam, beschrijving, doel, kamerId, boek);
            case "final":
                return new KamerFinal(naam, beschrijving, doel, kamerId, boek);
            case "scrum":
                switch (naam.toLowerCase()) {
                    case "dailyscrum":
                        return new KamerDailyScrum(naam, beschrijving, doel, kamerId, boek);
                    case "planning":
                        return new KamerPlanning(naam, beschrijving, doel, kamerId, boek);
                    case "retrospective":
                        return new KamerRetrospective(naam, beschrijving, doel, kamerId, boek);
                    case "review":
                        return new KamerReview(naam, beschrijving, doel, kamerId, boek);
                    case "scrumboard":
                        return new KamerScrumboard(naam, beschrijving, doel, kamerId, boek);
                    default:
                        System.out.println("Onbekende scrum kamer: " + naam);
                        return null;
                }
            default:
                System.out.println("Onbekend kamertype: " + type);
                return null;
        }
    }
}
