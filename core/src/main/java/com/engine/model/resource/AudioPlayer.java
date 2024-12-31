package com.engine.model.resource;

/**
 * Interface représentant un lecteur audio capable de jouer des sons.
 * <p>
 * Cette interface définit une méthode permettant de jouer un son spécifique, en contrôlant le volume.
 * Elle peut être utilisée pour implémenter différentes stratégies de gestion du son dans le moteur de jeu,
 * telles que le contrôle du volume ou la gestion des effets sonores.
 * </p>
 */
public interface AudioPlayer {

    /**
     * Joue un son spécifié avec un volume donné.
     * <p>
     * Cette méthode permet de lire un son à partir d'un nom de fichier ou d'une référence spécifiée.
     * Le volume du son peut être ajusté grâce au paramètre {@code volume}.
     * </p>
     *
     * @param soundName le nom du son à jouer (par exemple, le chemin du fichier ou une clé de ressource).
     * @param volume le volume auquel jouer le son, généralement compris entre 0.0f (silence) et 1.0f (volume maximal).
     */
    void playSound(String soundName, float volume);
}
