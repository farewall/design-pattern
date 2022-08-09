package observer.example;

public class ConcreteSubject extends Subject {
    public void doSomething() {
        super.notifyObservers();
    }
}
