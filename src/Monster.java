class Monster extends Character {
    private Kamer kamer;

    public Monster(String naam, String beschrijving, int characterID, int lives, Kamer kamer) {
        super(naam, beschrijving, characterID, lives);
        this.kamer = kamer;
    }


    public void monsterTonen() {
        System.out.println("Het monster " + naam + " bewaakt kamer: " + kamer.getNaam());
    }

    public void verliesLeven(){
        lives--;
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

    public String SirRat () {
        return "~~(__^·>";
    }

    public String SirRatTekst () {
        String tekst = "Piep! Mijn naam is Sir Rattington " + SirRat() + " , ridder van De Heilige Sprint, \nbeschermer van het SCRUM proces en bewaker van orde en structuur! Piep!\n"
        + "En JIJ... JIJ hebt de heilige regels van SCRUM genegeerd! Piep. Dit is een \nhalsmisdaad tegen het proces, piep! En daar zul je voor boeten!!!";
        return tekst;
    }

    public String sprinty () {
        return "c[○┬●]כ";
    }

    public String sprintyTekst () {
        String tekst = "WAARSCHUWING! SCRUM-ALARM ACTIEF!\n" +
                "Onregelmatigheden gedetecteerd in... biep... Sprint planning... Daily stand-ups\n" +
                "en... booeep... Definition of Done...\n\n" +
                "" +
                "Ik ben S.P.R.I.N.T.Y. "+ sprinty() +" , prototype facilitatierobot, versie 3.4, model KONIJN,\n" +
                "ontworpen om agile teams te begeleiden naar maximale velocity. Booeep,\n" +
                "maar JIJ... JIJ hebt mijn processo laten vastlopen met je chaotische workflow!\n\n" +
                "" +
                "Story points? Inconsistent! Taken? Niet opgesplitst! Teamcommunicatie? EEN\n" +
                "RAMP! Bieeep.\n\n" +
                "" +
                "Jouw iteratief onvermogen is een aanval op alles waar ik voor gecompileerd\n" +
                "ben. En nu... NU IS HET TIJD VOOR EEN RETROSPECTIEVE REINIGING! booeep.";
        return tekst;
    }
}