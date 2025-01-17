package com.engine.model.map;

import com.badlogic.gdx.math.Vector2;
import com.engine.model.entity.components.hitBox.HitBox;

/**
 * Interface responsable de la gestion des collisions dans le jeu, notamment avec les tuiles de la carte et les objets du monde.
 * <p>
 * Les classes qui implémentent cette interface sont chargées de vérifier les collisions entre des entités et la carte,
 * ainsi que de gérer les déplacements des entités en prenant en compte les collisions et les zones interdites.
 * </p>
 */
public interface IMapCollisionChecker {

    /**
     * Récupère la largeur d'une tuile de la carte.
     *
     * @return la largeur d'une tuile en pixels.
     */
    int getTileWidth();

    /**
     * Récupère la hauteur d'une tuile de la carte.
     *
     * @return la hauteur d'une tuile en pixels.
     */
    int getTileHeight();

    /**
     * Récupère la largeur de la carte.
     *
     * @return la largeur de la carte en nombre de tuiles.
     */
    int getMapWidth();

    /**
     * Récupère la hauteur de la carte.
     *
     * @return la hauteur de la carte en nombre de tuiles.
     */
    int getMapHeight();

    /**
     * Vérifie si deux rectangles se chevauchent, indiquant une collision.
     *
     * @param rect1 le premier rectangle à vérifier.
     * @param rect2 le second rectangle à vérifier.
     * @return {@code true} si les deux rectangles se chevauchent, sinon {@code false}.
     */
    boolean isCollision(HitBox rect1, HitBox rect2);

    /**
     * Vérifie si l'entité représentée par le rectangle donné entre en collision avec des objets sur la carte.
     *
     * @param hitBox le hitbox représentant l'entité à vérifier.
     * @return {@code true} si l'entité entre en collision avec un objet, sinon {@code false}.
     */
    boolean checkCollisionWithObjects(HitBox hitBox);

    /**
     * Vérifie si le rectangle donné est hors des limites de la carte.
     *
     * @param hitBox le HitBox à vérifier.
     * @return {@code true} si le rectangle est en dehors des limites de la carte, sinon {@code false}.
     */
    boolean isOutOfBounds(HitBox hitBox);

    /**
     * Vérifie si le rectangle donné se trouve dans une zone restreinte (par exemple, une zone interdite).
     *
     * @param hitBox le hitBox à vérifier.
     * @return {@code true} si le rectangle est dans une zone restreinte, sinon {@code false}.
     */
    boolean isInRestrictedZone(HitBox hitBox);

    /**
     * Résout les collisions en ajustant la position de l'entité en fonction de la direction donnée.
     * <p>
     * Cette méthode est utilisée pour gérer les mouvements des entités tout en évitant les collisions avec d'autres objets ou murs.
     * </p>
     *
     * @param position la position actuelle de l'entité.
     * @param direction la direction du déplacement de l'entité.
     */
    void resolveCollisions(HitBox hitBox, Vector2 position, Vector2 direction);
}
