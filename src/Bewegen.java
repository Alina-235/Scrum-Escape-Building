import java.util.Scanner;

interface move{
    void bewegen();
}

class Bewegen implements move {
    private int kamer;
    static Scanner scanner = new Scanner(System.in);
    databaseSelect select = new databaseSelect();

    public Bewegen(int kamer) {
        this.kamer = kamer;
    }

    public void bewegen() {
        Kamer huidigekamer = select.getKamerById(kamer);
        while (true) {
            System.out.println("beweeg tussen kamers met a/d: ");
            String movewithkey = scanner.nextLine();
            if (movewithkey.equals("a")) {
                kamer--;
                select.getKamerById(kamer);
                System.out.println(kamer);
            } else if (movewithkey.equals("d")) {
                kamer++;
                System.out.println(kamer);
            }
        }
    }
}