package com.engine.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.engine.model.entity.player.Player;

public class GameController {

    final Player player;
    private OrthographicCamera cam;
    private Vector3 mouseWorldPosition = new Vector3();  // Utiliser un Vector3 pour la position de la souris

    public GameController(Player player , OrthographicCamera cam ) {
        this.player = player;
        this.cam = cam;
    }

    private void updatePlayerRotation() {
        // Obtenir la position du joueur
        Vector2 playerPos = player.getPosition();

        // Transformer les coordonnées de la souris en utilisant un Vector3
        mouseWorldPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        cam.unproject(mouseWorldPosition);  // Convertit les coordonnées écran en coordonnées monde

        // Calculer l'angle de rotation vers la souris
        float angle = MathUtils.atan2(mouseWorldPosition.y - (playerPos.y+player.getHeight()/2) ,
            mouseWorldPosition.x - ( playerPos.x + player.getWidth()/2) )  * MathUtils.radiansToDegrees;

        // Mettre à jour l'angle de rotation dans le modèle joueur
        player.setRotationAngle(angle);
    }

    public void update(float dt) {
        updateCamera();
        updatePlayerRotation(); // Met à jour la rotation du joueur vers la souris

    }

    public void updateCamera() {
        // Largeur et hauteur de la carte en pixels
        int mapWidth = 40 * 32;
        int mapHeight = 40 * 32;

        // Définition des marges
        float marginLeft = cam.viewportWidth/2; // Bord gauche
        float marginRight = mapWidth - cam.viewportWidth/2; // Bord droit
        float marginBottom = cam.viewportHeight/2; // Bord inférieur
        float marginTop = mapHeight - cam.viewportHeight/2; // Bord supérieur

        // Suivre le joueur dans les limites
        float playerX = player.getPosition().x;
        float playerY = player.getPosition().y;

        // Ajuster la position X de la caméra
        if (playerX < marginLeft) {
            cam.position.x = marginLeft ; // Fixer la caméra à la marge gauche
        } else if (playerX > marginRight) {
            cam.position.x = marginRight ; // Fixer la caméra à la marge droite
        } else {
            cam.position.x = playerX ; // Suivre le joueur
        }

        // Ajuster la position Y de la caméra
        if (playerY < marginBottom) {
            cam.position.y = marginBottom ; // Fixer la caméra à la marge du bas
        } else if (playerY > marginTop) {
            cam.position.y = marginTop ; // Fixer la caméra à la marge du haut
        } else {
            cam.position.y = playerY; // Suivre le joueur
        }

        // Mettre à jour la caméra
        cam.update();
    }

}
