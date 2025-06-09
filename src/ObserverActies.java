import java.util.ArrayList;

class ObserverActies {
    private ArrayList<SpelerObserver> observers = new ArrayList<>();

    public void addObserver(SpelerObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Speler speler, String gebeurtenis) {
        for (SpelerObserver observer : observers) {
            observer.update(speler, gebeurtenis);
        }
    }
}