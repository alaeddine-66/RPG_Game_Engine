package com.engine.model.resource;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.audio.Sound;


/**
 * Interface représentant un chargeur de ressources générique.
 * <p>
 * Cette interface définit la méthode nécessaire pour charger une ressource à partir d'un chemin donné.
 * Elle est générique afin de permettre le chargement de différents types de ressources (images, sons, données, etc.)
 * en fonction du type spécifié lors de l'implémentation.
 * </p>
 *
 * @param <T> le type de la ressource à charger (par exemple, {@link Texture}, {@link Sound}, etc.).
 */
public interface ResourceLoader<T> {

    /**
     * Charge une ressource à partir du chemin spécifié.
     * <p>
     * Cette méthode prend un chemin de fichier en entrée et renvoie une ressource de type {@code T}.
     * Le comportement exact du chargement dépend de l'implémentation spécifique de cette interface.
     * </p>
     *
     * @param path le chemin du fichier à charger.
     * @return la ressource chargée, de type {@code T}.
     */
    T load(String path);
}
