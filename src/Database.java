import java.sql.*;
import java.util.ArrayList;

class Database {
    private static String url = "jdbc:mysql://localhost:3306/scrum_escape_building";
    private static String username = "root";
    private static String password = "A";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Databaseverbinding mislukt: " + e.getMessage());
            return null;
        }
    }
}

class databaseInsert extends Database{

    public void InsertCharacter(String naam) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO gamecharacter (naam) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, naam);

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Karakter " + naam + " is toegevoegd.");
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

    public void saveGameCharacter(int characterId, String naam, String beschrijving, int levens, int huidigeKamerId, String type) {
        try (Connection conn = getConnection()) {
            String checkSql = "SELECT character_id FROM gamecharacter WHERE character_id = ?";
            PreparedStatement checkStmt = conn.prepareStatement(checkSql);
            checkStmt.setInt(1, characterId);
            ResultSet rs = checkStmt.executeQuery();

            if (rs.next()) {
                String updateSql = "UPDATE gamecharacter SET naam = ?, beschrijving = ?, levens = ?, huidige_kamer = ?, type = ? WHERE character_id = ?";
                PreparedStatement updateStmt = conn.prepareStatement(updateSql);
                updateStmt.setString(1, naam);
                updateStmt.setString(2, beschrijving);
                updateStmt.setInt(3, levens);
                updateStmt.setInt(4, huidigeKamerId);
                updateStmt.setString(5, type);
                updateStmt.setInt(6, characterId);
                updateStmt.executeUpdate();
                System.out.println("Karakter " + naam + " is bijgewerkt.");
            } else {

                String insertSql = "INSERT INTO gamecharacter (character_id, naam, beschrijving, levens, huidige_kamer, type) VALUES (?, ?, ?, ?, ?, ?)";
                PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                insertStmt.setInt(1, characterId);
                insertStmt.setString(2, naam);
                insertStmt.setString(3, beschrijving);
                insertStmt.setInt(4, levens);
                insertStmt.setInt(5, huidigeKamerId);
                insertStmt.setString(6, type);
                insertStmt.executeUpdate();
                System.out.println("Karakter " + naam + " is toegevoegd.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



class databaseSelect extends Database {

    public void SelectCharacter(int nummer) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM gamecharacter WHERE character_id = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, nummer);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                System.out.println("U speelt nu als karakter " + nummer);
                System.out.println();
            }
            else{
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
                String naam = result.getString ("Naam");
                String beschrijving = result.getString("beschrijving");

                System.out.println("Kamer: " + naam + "\nBeschrijving: " + beschrijving);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Kamer getKamerById(int kamerId) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM kamer WHERE kamer_id = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, kamerId);

            ResultSet result = stmt.executeQuery();

            if (result.next()) {
                int id = result.getInt("kamer_id");
                String naam = result.getString("naam");
                String beschrijving = result.getString("beschrijving");
                String type = result.getString("type");

                switch (type.toLowerCase()) {
                    case "daily":
                        return new KamerDailyScrum(naam, beschrijving, type);
                    case "planning":
                        return new KamerPlanning(naam, beschrijving, type);
                    case "review":
                        return new KamerReview(naam, beschrijving, type);
                    case "scrumboard":
                        return new KamerScrumboard(naam, beschrijving, type);
                    case "retro":
                        return new KamerRetrospective(naam, beschrijving, type);
                    case "start":
                        return new KamerDailyScrum(naam, beschrijving, type);
                    default:
                        System.out.println("Onbekend kamer: " + naam);
                        return null;
                }
            } else {
                System.out.println("Geen kamer gevonden met ID: " + kamerId);
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

    public Speler getSpelerByNaam(String naam) {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM gamecharacter WHERE naam = ? AND type = 'speler'";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, naam);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int characterId = rs.getInt("character_id");
                String beschrijving = rs.getString("beschrijving");
                int levens = rs.getInt("levens");
                int huidigeKamerId = rs.getInt("huidige_kamer");

                Speler speler = new Speler(naam, characterId);
                speler.setBeschrijving(beschrijving);
                speler.setLives(levens);

                Kamer kamer = getKamerById(huidigeKamerId);
                if (kamer != null) {
                    speler.moveTo(kamer);
                }

                System.out.println("Speler " + naam + " geladen.");
                return speler;
            } else {
                System.out.println("Speler " + naam + " niet gevonden.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Speler SpelerLogin(String naam) {
        Speler speler = getSpelerByNaam(naam);

        if (speler == null) {
            speler = new Speler(naam, 1);
            speler.setBeschrijving("Nieuwe speler");
            speler.setLives(3);
            Kamer startKamer = getKamerById(1);
            speler.moveTo(startKamer);

            new databaseInsert().saveGameCharacter(
                    speler.getCharacterID(),
                    speler.getNaam(),
                    speler.getBeschrijving(),
                    speler.getLives(),
                    startKamer.getKamerId(),
                    "speler"
            );

            System.out.println("Nieuwe speler aangemaakt.");
        } else {
            System.out.println("Welkom terug, " + speler.getNaam());
        }

        return speler;
    }


}