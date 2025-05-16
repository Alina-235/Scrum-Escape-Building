import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static String url = "jdbc:mysql://localhost:3306/scrum_escape_building";
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

class databaseInsert extends Database {

    public void InsertCharacter(String naam) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO gamecharacter (naam) VALUES (?)";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, naam);
            stmt.executeUpdate();
            System.out.println("Karakter " + naam + " is toegevoegd.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void InsertVragen(String vraag, String antwoord) {
        try (Connection conn = getConnection()) {
            String sql = "INSERT INTO vraag (vraag, antwoord) VALUES (?, ?)"; // Let op: tabel heet 'vraag' (zonder 'n')!
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, vraag);
            stmt.setString(2, antwoord);
            stmt.executeUpdate();
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
            } else {
                System.out.println("Deze karakter is niet gevonden.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void SelectVragen() {
        try (Connection conn = getConnection()) {
            String sql = "SELECT * FROM vraag";  // Let op: tabelnaam moet kloppen
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
}
