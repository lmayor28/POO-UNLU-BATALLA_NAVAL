package version_0_3;
import java.util.ArrayList;

public abstract class Subject {

    ArrayList<Observer> observers = new ArrayList<Observer>();

    public void attach(Observer observer){
        if (observers.contains(observer)){
            return;
        }

        observers.add(observer);
    }


    public void detach(Observer observer){
        observers.remove(observer);
    }


    public void notifyObservers(Object arg){
        for (Observer observer : observers){
            observer.update(arg);
        }
    }




}
