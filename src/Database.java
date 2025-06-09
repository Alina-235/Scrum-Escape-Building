import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/scrum_escape_building";
    private static String username = "root";
    private static String password = "Spotify123!";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Databaseverbinding mislukt: " + e.getMessage());
            return null;
        }
    }
}

class databaseInsert extends Database{


    public int insertNewGameCharacter(String naam, String beschrijving, int levens, int huidigeKamerId, String type) {
        String sql = "INSERT INTO gamecharacter (naam, beschrijving, levens, huidige_kamer, type) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, naam);
            stmt.setString(2, beschrijving);
            stmt.setInt(3, levens);
            stmt.setInt(4, huidigeKamerId);
            stmt.setString(5, type);

            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) throw new SQLException("Insert failed, no rows affected.");

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("Insert failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public void updateGameCharacter(int characterId, String naam, String beschrijving, int levens, int huidigeKamerId, String type) {
        String sql = "UPDATE gamecharacter SET naam=?, beschrijving=?, levens=?, huidige_kamer=?, type=? WHERE character_id=?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, naam);
            stmt.setString(2, beschrijving);
            stmt.setInt(3, levens);
            stmt.setInt(4, huidigeKamerId);
            stmt.setString(5, type);
            stmt.setInt(6, characterId);

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    public void saveGameCharacter(Speler speler) {
        try (Connection conn = getConnection()) {
            if (speler.getCharacterID() <= 0) {
                String insertSql = "INSERT INTO gamecharacter (naam, beschrijving, levens, huidige_kamer, type) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
                insertStmt.setString(1, speler.getNaam());
                insertStmt.setString(2, speler.getBeschrijving());
                insertStmt.setInt(3, speler.getLives());
                insertStmt.setInt(4, speler.getHuidigeKamer() != null ? speler.getHuidigeKamer().getKamerId() : 1);
                insertStmt.setString(5, "speler");
                insertStmt.executeUpdate();

                ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int newId = generatedKeys.getInt(1);
                    speler.setCharacterID(newId);
                }
                System.out.println("Nieuwe speler is toegevoegd met ID: " + speler.getCharacterID());
            } else {

                String updateSql = "UPDATE gamecharacter SET naam = ?, beschrijving = ?, levens = ?, huidige_kamer = ?, type = ? WHERE character_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, speler.getNaam());
                updateStmt.setString(2, speler.getBeschrijving());
                updateStmt.setInt(3, speler.getLives());
                updateStmt.setInt(4, speler.getHuidigeKamer() != null ? speler.getHuidigeKamer().getKamerId() : 1);
                updateStmt.setString(5, "speler");
                updateStmt.setInt(6, speler.getCharacterID());
                updateStmt.executeUpdate();
                System.out.println("Speler " + speler.getNaam() + " is bijgewerkt.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertVragen(String vraag, String antwoord) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO vragen (vraag, antwoord) VALUES (?,?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vraag);
            stmt.setString(2, antwoord);

            int rowsInserted = stmt.executeUpdate();
            System.out.println("De vraag is toegevoegd.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

class databaseSelect extends Database {

    public void SelectCharacter(int nummer) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM gamecharacter WHERE nummer = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nummer);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                System.out.println("U speelt nu als karakter " + nummer);
                System.out.println();
            } else {
                System.out.println("Deze karakter is niet gevonden.");
                System.out.println();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SelectVragen() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM vragen";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("id");
                String vraag = result.getString("vraag");

                System.out.println("ID: " + id + ", Vraag: " + vraag);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void selectKamer() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM kamer";
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet result = stmt.executeQuery();

            while (result.next()) {
                int id = result.getInt("kamer_id");
                String naam = result.getString("Naam");
                String beschrijving = result.getString("beschrijving");

                System.out.println("Kamer: " + naam + "\nBeschrijving: " + beschrijving);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kamer getKamerById(int kamerId) {
        String sql = "SELECT * FROM kamer WHERE kamer_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, kamerId);

            try (ResultSet result = stmt.executeQuery()) {
                if (result.next()) {
                    int id = result.getInt("kamer_id");
                    String naam = result.getString("naam");
                    String beschrijving = result.getString("beschrijving");
                    String type = result.getString("type");
                    String doel = result.getString("doel");

                    if (type == null) {
                        System.out.println("Kamertype is null voor kamer ID: " + kamerId);
                        return null;
                    }

                    switch (type.toLowerCase()) {
                        case "scrum":
                            return new KamerDailyScrum(naam, beschrijving, doel, id);
                        case "daily":
                            return new KamerDailyScrum(naam, beschrijving, doel, id);
                        case "planning":
                            return new KamerPlanning(naam, beschrijving, doel, id);
                        case "review":
                            return new KamerReview(naam, beschrijving, doel, id);
                        case "scrumboard":
                            return new KamerScrumboard(naam, beschrijving, doel, id);
                        case "retro":
                            return new KamerRetrospective(naam, beschrijving, doel, id);
                        case "start":
                            return new KamerDailyScrum(naam, beschrijving, doel, id);
                        default:
                            System.out.println("Onbekend kamertype: " + type);
                            return null;
                    }
                } else {
                    System.out.println("Geen kamer gevonden met ID: " + kamerId);
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Vragen> getVragenVoorKamer(int kamerId) {
        ArrayList<Vragen> vragenLijst = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT vraag, antwoord FROM vraag WHERE kamer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, kamerId);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String vraagTekst = rs.getString("vraag");
                String juistAntwoord = rs.getString("antwoord");

                VraagStrategie strategie = new OpenInvulStrategie(juistAntwoord);
                Vragen vraagObject = new Vragen(vraagTekst, strategie, null);
                vragenLijst.add(vraagObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vragenLijst;
    }

    public ArrayList<Hint> getHints() {
        ArrayList<Hint> hintLijst = new ArrayList<>();

        try (Connection conn = getConnection()) {
            String sql = "SELECT tekst, type FROM hint";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                String tekst = rs.getString("tekst");
                String type = rs.getString("type");

                switch (type.toLowerCase()) {
                    case "funny":
                        hintLijst.add(new FunnyHint(tekst));
                        break;
                    case "help":
                        hintLijst.add(new HelpHint(tekst));
                        break;
                    default:
                        System.out.println("Onbekend hint-type: " + type);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hintLijst;
    }

    public Speler getSpelerByNaam(String naam) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM gamecharacter WHERE BINARY naam = ? AND type = 'speler'"
             )) {
            stmt.setString(1, naam);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int characterId = rs.getInt("character_id");
                String beschrijving = rs.getString("beschrijving");
                int levens = rs.getInt("levens");


                int huidigeKamerId = rs.getInt("huidige_kamer");
                if (rs.wasNull() || huidigeKamerId < 1) {
                    huidigeKamerId = 1;
                }

                Kamer huidigeKamer = getKamerById(huidigeKamerId);

                Speler speler = new Speler(naam, characterId);
                speler.setBeschrijving(beschrijving);
                speler.setLives(levens);
                speler.moveTo(huidigeKamer);
                return speler;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }


    public Speler SpelerLogin(String naam) {
        Speler speler = getSpelerByNaam(naam);

        if (speler == null) {
            speler = new Speler(naam);
            speler.setBeschrijving("Nieuwe speler");
            speler.setLives(3);
            Kamer startKamer = getKamerById(1);
            speler.moveTo(startKamer);

            speler.saveToDatabase();
            System.out.println("Nieuwe speler aangemaakt.");
        } else {
            System.out.println("Welkom terug, " + speler.getNaam());
        }

        return speler;
    }


}

class Update extends Database {

    public void updateVoortgang(String speler, String voortgang) {
        try (Connection conn = getConnection()) {
            String sql = "UPDATE Speler SET voortgang = ? WHERE speler = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, voortgang);
            stmt.setString(2, speler);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Voortgang succesvol bijgewerkt.");
            } else {
                System.out.println("Geen speler gevonden met de naam: " + speler);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}