public interface Hint {
    String getHint();
}

class HelpHint implements Hint {
    private final String tekst;

    public HelpHint(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String getHint() {
        return tekst;
    }
}

class FunnyHint implements Hint {
    private final String tekst;

    public FunnyHint(String tekst) {
        this.tekst = tekst;
    }

    @Override
    public String getHint() {
        return tekst;
    }
}


