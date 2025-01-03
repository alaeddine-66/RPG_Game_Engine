package com.engine.model.ObserverPattern;

/**
 * Interface représentant un observateur dans un système de notification d'événements.
 * <p>
 * Les objets qui implémentent cette interface peuvent s'abonner à un objet observable pour recevoir des notifications
 * lorsque des événements se produisent. L'observateur doit définir la méthode {@link #update(Event)} pour réagir
 * aux événements qui lui sont notifiés.
 * </p>
 */
public interface Observer {

    /**
     * Méthode appelée pour mettre à jour l'observateur lorsqu'un événement se produit.
     * <p>
     * Cette méthode permet à l'observateur de réagir à l'événement qui lui a été notifié par un objet observable.
     * </p>
     *
     * @param event l'événement qui a eu lieu et qui doit être traité par l'observateur.
     */
    void update(Event event);
}
