import java.sql.*;
import java.util.ArrayList;

class Database {
    private static String url = "jdbc:mysql://localhost:3306/scrum_escape_buidling";
    private static String username = "root";
    private static String password = "AGao2005.";

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
}