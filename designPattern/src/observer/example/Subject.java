package observer.example;

import java.util.HashSet;
import java.util.Set;

public abstract class Subject {
    private Set<Observer> obsSet = new HashSet<>();

    public void addObserver(Observer o) {
        this.obsSet.add(o);
    }

    public void delObserver(Observer o) {
        this.obsSet.remove(o);
    }

    // 通知观察者
    public void notifyObservers() {
        for (Observer observer : obsSet) {
            observer.update();
        }
    }
}
