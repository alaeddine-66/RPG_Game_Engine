package com.engine.model.ObserverPattern;

import java.util.ArrayList;
import java.util.List;


/**
 * Classe abstraite représentant un objet observable dans un système de notification d'événements.
 * <p>
 * Cette classe permet de gérer une liste d'observateurs et de les notifier lorsqu'un événement se produit.
 * Elle implémente la gestion de l'ajout, de la suppression et de la notification des observateurs.
 * </p>
 */
public abstract class Observable {

    private final List<Observer> observers = new ArrayList<>();

    /**
     * Ajoute un observateur à la liste des observateurs de cet objet observable.
     * <p>
     * Les observateurs sont des objets qui souhaitent être notifiés lorsque des événements se produisent.
     * </p>
     *
     * @param observer l'observateur à ajouter.
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * Supprime un observateur de la liste des observateurs de cet objet observable.
     * <p>
     * Cela empêche l'observateur de recevoir des notifications futures.
     * </p>
     *
     * @param observer l'observateur à supprimer.
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * Notifie tous les observateurs enregistrés qu'un événement a eu lieu.
     * <p>
     * Cette méthode appelle la méthode {@link Observer#update(Event)} de chaque observateur inscrit pour l'informer de l'événement.
     * </p>
     *
     * @param event l'événement qui se produit et qui doit être notifié aux observateurs.
     */
    protected void notifyObservers(Event event) {
        for (Observer observer : observers) {
            observer.update(event);
        }
    }
}
