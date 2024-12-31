package com.engine.model.ObserverPattern;


/**
 * Interface représentant un objet observable dans un système de notification.
 * <p>
 * Les objets qui implémentent cette interface peuvent enregistrer des observateurs, les notifier d'événements et
 * gérer l'ajout ou la suppression de ces observateurs. Cela permet de mettre en place un modèle de type observer
 * pour les notifications d'événements dans l'application.
 * </p>
 */
public interface IObservable {

    /**
     * Ajoute un observateur à la liste des observateurs de cet objet.
     * <p>
     * Un observateur peut être un objet qui souhaite être notifié lorsqu'un événement survient.
     * </p>
     *
     * @param observer l'observateur à ajouter à la liste.
     */
    void addObserver(Observer observer);

    /**
     * Supprime un observateur de la liste des observateurs de cet objet.
     * <p>
     * Cette méthode est utilisée pour arrêter de recevoir des notifications pour cet objet.
     * </p>
     *
     * @param observer l'observateur à supprimer.
     */
    void removeObserver(Observer observer);

    /**
     * Notifie tous les observateurs enregistrés qu'un événement a eu lieu.
     * <p>
     * Cette méthode informe tous les observateurs de l'événement spécifié afin qu'ils puissent réagir en conséquence.
     * </p>
     *
     * @param event l'événement qui se produit et qui doit être notifié aux observateurs.
     */
    void notifyObservers(Event event);
}
