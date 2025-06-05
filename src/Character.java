abstract class Character {

    protected String naam;
    protected String beschrijving;
    protected int characterID;
    protected int lives;
    protected boolean verslagen;

    public Character(String naam, String beschrijving, int CharacterID, int lives) {
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.characterID = CharacterID;
        this.lives = lives;
        this.verslagen = false;
    }

    public String getNaam() {
        return naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving){
        this.beschrijving = beschrijving;
    }

    public int getLives(){
        return lives;
    }

    public void setLives(int lives){
        this.lives = lives;
    }

    public int getCharacterID(){
        return characterID;
    }

    public boolean isVerslagen(){
        return verslagen;
    }

    public void setVerslagen(boolean verslagen){
        this.verslagen = verslagen;
    }
}