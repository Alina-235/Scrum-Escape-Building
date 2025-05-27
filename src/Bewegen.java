import java.util.Scanner;

interface move{
    void bewegen();
}

class Bewegen implements move {
    static Scanner scanner = new Scanner(System.in);
    databaseSelect select = new databaseSelect();

    public void bewegen() {
        int kamer = 1;
        while (true) {
            System.out.println("beweeg tussen kamers met a/d: ");
            String movewithkey = scanner.nextLine();
            if (movewithkey.equals("a")) {
                kamer = kamer - 1;
                select.getKamerById(kamer);
                System.out.println(kamer);
            } else if (movewithkey.equals("d")) {
                kamer = kamer + 1;
                System.out.println(kamer);
            }
        }
    }
}