import java.util.ArrayList;
import java.util.Scanner;

class Assistent {
    private final databaseSelect db = new databaseSelect();
    private final Scanner scanner = new Scanner(System.in);

    public void activeer(Kamer kamer, Vragen vraag) {
        System.out.println("Welke soort hint wil je? Typ 'help' voor inhoudelijk of 'funny' voor grappig:");
        String keuze = scanner.nextLine().toLowerCase();

        Hint gekozenHint = haalHintVanType(kamer.getKamerId(), keuze);
        if (gekozenHint != null) {
            System.out.println("Hint: " + gekozenHint.getHint());
        } else {
            System.out.println("Geen hints van dit type beschikbaar.");
        }

        System.out.println("Educatief hulpmiddel: " + geefEducatiefHulpmiddel(vraag));
        System.out.println("Motivatie: " + geefMotiverendeBoodschap());
    }

    private Hint haalHintVanType(int kamerId, String type) {
        ArrayList<Hint> hints = db.getHintsVoorKamer(kamerId);

        return hints.stream()
                .filter(h -> (type.equals("funny") && h instanceof FunnyHint) ||
                        (type.equals("help") && h instanceof HelpHint))
                .findAny()
                .orElse(null);
    }

    private String geefEducatiefHulpmiddel(Vragen vraag) {
        String uitleg = vraag.getUitleg();

        return uitleg != null && !uitleg.isBlank()
                ? uitleg
                : "Geen educatief hulpmiddel beschikbaar.";
    }

    private String geefMotiverendeBoodschap() {
        String[] boodschappen = {
                "Je komt er wel!", "Nog één kamer te gaan!", "Je doet het goed!", "Hou vol!"
        };
        return boodschappen[(int) (Math.random() * boodschappen.length)];
    }
}
