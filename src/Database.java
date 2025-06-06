import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/escapescrumbuilding";
    private static String username = "soulali";
    private static String password = "Gammaverhuisdoos1";

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
                String doel = result.getString("doel"); // ✅ now retrieved

                switch (type.toLowerCase()) {
                    case "daily":
                        return new KamerDailyScrum(naam, beschrijving, doel);
                    case "planning":
                        return new KamerPlanning(naam, beschrijving, doel);
                    case "review":
                        return new KamerReview(naam, beschrijving, doel);
                    case "scrumboard":
                        return new KamerScrumboard(naam, beschrijving, doel);
                    case "retro":
                        return new KamerRetrospective(naam, beschrijving, doel);
                    case "start":
                        return new KamerDailyScrum(naam, beschrijving, doel);
                    default:
                        System.out.println("Onbekend kamertype: " + type);
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