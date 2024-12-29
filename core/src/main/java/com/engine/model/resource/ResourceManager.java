package com.engine.model.resource;

import java.util.HashMap;
import java.util.Map;

public class ResourceManager {
    private final Map<String, ResourceLoader<?>> loaders = new HashMap<>();
    private final Map<String, Map<String, ?>> resourceMaps = new HashMap<>();

    public ResourceManager(){
        registerLoader("Texture", new TextureLoader());
        registerLoader("TextureRegion", new TextureRegionLoader());
    }

    /**
     * Enregistre un loader pour un type spécifique de ressource.
     *
     * @param resourceType Le type de ressource (par exemple, "texture" ou "sound").
     * @param loader       Le loader responsable de charger ce type de ressource.
     */
    public void registerLoader(String resourceType, ResourceLoader<?> loader) {
        loaders.put(resourceType, loader);
        resourceMaps.put(resourceType, new HashMap<>());
    }

    /**
     * Obtient une ressource du type spécifié.
     *
     * @param resourceType Le type de ressource (par exemple, "texture" ou "sound").
     * @param path         Le chemin de la ressource.
     * @param <T>          Le type de ressource.
     * @return La ressource chargée.
     */
    public <T> T getResource(String resourceType, String path) {
        Map<String, T> resourceMap = (Map<String, T>) resourceMaps.get(resourceType);
        if (resourceMap == null) {
            throw new IllegalStateException("No loader registered for resource type: " + resourceType);
        }

        if (!resourceMap.containsKey(path)) {
            ResourceLoader<T> loader = (ResourceLoader<T>) loaders.get(resourceType);
            if (loader == null) {
                throw new IllegalStateException("No loader found for resource type: " + resourceType);
            }
            resourceMap.put(path, loader.load(path));
        }

        return resourceMap.get(path);
    }
}
