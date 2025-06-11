import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MonsterFactory {
    private static final List<Monster> monsterTemplates = new ArrayList<>();

    static {
        monsterTemplates.add(new Monster(
                "Sir Rattington",
                "Piep! Mijn naam is Sir Rattington ~~(__^·>, ridder van De Heilige Sprint,\n" +
                        "beschermer van het SCRUM proces en bewaker van orde en structuur! Piep!\n" +
                        "En JIJ... JIJ hebt de heilige regels van SCRUM genegeerd! Piep. Dit is een\n" +
                        "halsmisdaad tegen het proces, piep! En daar zul je voor boeten!!!",
                1, 2,
                "~~(__^·>"
        ));

        monsterTemplates.add(new Monster(
                "Sprinty",
                "WAARSCHUWING! SCRUM-ALARM ACTIEF!\n" +
                        "Onregelmatigheden gedetecteerd in... biep... Sprint planning... Daily stand-ups\n" +
                        "en... booeep... Definition of Done...\n\n" +
                        "Ik ben S.P.R.I.N.T.Y. c[○┬●]כ , prototype facilitatierobot, versie 3.4, model KONIJN,\n" +
                        "ontworpen om agile teams te begeleiden naar maximale velocity. Booeep,\n" +
                        "maar JIJ... JIJ hebt mijn processo laten vastlopen met je chaotische workflow!\n\n" +
                        "Story points? Inconsistent! Taken? Niet opgesplitst! Teamcommunicatie? EEN\n" +
                        "RAMP! Bieeep.\n\n" +
                        "Jouw iteratief onvermogen is een aanval op alles waar ik voor gecompileerd\n" +
                        "ben. En nu... NU IS HET TIJD VOOR EEN RETROSPECTIEVE REINIGING! booeep.",
                2, 3,
                "c[○┬●]כ"
        ));
    }

    public static Monster getRandomMonster() {
        Random rand = new Random();
        Monster template = monsterTemplates.get(rand.nextInt(monsterTemplates.size()));
        return new Monster(template); // Use copy constructor
    }
}
