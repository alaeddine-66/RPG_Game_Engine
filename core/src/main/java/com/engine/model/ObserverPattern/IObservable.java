package com.engine.model.ObserverPattern;

public interface IObservable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(Event event);
}
