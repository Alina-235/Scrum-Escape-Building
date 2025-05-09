public abstract class Character {

    protected String naam;
    protected String beschrijving;
    protected int CharacterID;
    protected int lives;
    protected boolean verslagen;

    public Character(String naam, String beschrijving, int CharacterID, int lives) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.CharacterID = CharacterID;
        this.lives = lives;
        this.verslagen = false;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public int getLives(){
        return lives;
    }

    public int setLives(int lives){
        return lives;
    }

    public int getCharacterID(){
        return CharacterID;
    }

    public boolean isVerslagen(){
        return verslagen;
    }

    public void setVerslagen(boolean verslagen){
        this.verslagen = verslagen;
    }
}
