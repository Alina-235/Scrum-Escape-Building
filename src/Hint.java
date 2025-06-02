public interface Hint {
    String getHint();
}

class HelpHint implements Hint {
    @Override
    public String getHint() {
        return "";
    }
}

class FunnyHint implements Hint {
    @Override
    public String getHint() {
        return "";
    }
}
