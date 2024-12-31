package com.engine.model.weapon.upgrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.resource.DataManager;
import com.engine.model.weapon.model.AbstractWeapon;


/**
 * Classe responsable de la gestion des améliorations d'armes dans le jeu.
 * <p>
 * Le {@code UpgradeManager} est chargé de charger les données d'amélioration à partir d'un fichier,
 * de gérer les effets d'amélioration disponibles et d'appliquer ces effets sur les armes.
 * </p>
 */
public class UpgradeManager {

    private List<UpgradeWeaponData> availableUpgrades; // Liste des améliorations disponibles
    private Map<String, UpgradeEffect> effectMap; // Carte des effets d'amélioration

    /**
     * Charge les améliorations à partir d'un fichier JSON.
     * <p>
     * Cette méthode charge un fichier JSON contenant les données d'amélioration et initialise
     * la liste des améliorations disponibles. Elle remplit également la carte des effets d'amélioration.
     * </p>
     *
     * @param filePath le chemin vers le fichier JSON contenant les données des améliorations.
     */
    public void loadUpgrades(String filePath) {
        UpgradeDataList upgradeDataList = DataManager.loadJsonData(filePath, UpgradeDataList.class);
        this.availableUpgrades = upgradeDataList.getUpgrades();
        effectMap = new HashMap<>();
    }

    /**
     * Ajoute un effet d'amélioration personnalisé.
     * <p>
     * Cette méthode permet à l'utilisateur d'ajouter des effets d'amélioration spécifiques au système.
     * Par exemple, un utilisateur pourrait ajouter un effet qui augmente les dégâts d'une arme.
     * </p>
     *
     * @param effectName le nom de l'effet.
     * @param effect l'effet d'amélioration à ajouter.
     */
    public void addEffect(String effectName, UpgradeEffect effect) {
        effectMap.put(effectName, effect);
    }

    /**
     * Récupère un effet d'amélioration par son nom.
     *
     * @param name le nom de l'effet à récupérer.
     * @return l'effet d'amélioration associé au nom donné.
     */
    public UpgradeEffect getEffect(String name) {
        return effectMap.get(name);
    }

    /**
     * Applique une amélioration sur une arme.
     * <p>
     * Cette méthode applique l'effet d'amélioration spécifié sur une arme donnée.
     * </p>
     *
     * @param upgrade les données de l'amélioration à appliquer.
     * @param weapon l'arme sur laquelle l'amélioration sera appliquée.
     */
    public void applyUpgrade(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        UpgradeEffect effect = getEffect(upgrade.getEffect());
        effect.apply(upgrade, weapon);
    }

    /**
     * Sélectionne aléatoirement une amélioration parmi celles disponibles.
     * <p>
     * Cette méthode permet de récupérer une amélioration au hasard dans la liste des améliorations disponibles.
     * </p>
     *
     * @return Une {@code UpgradeWeaponData} choisie aléatoirement.
     */
    public UpgradeWeaponData getRandomUpgrade() {
        return availableUpgrades.get((int) (Math.random() * availableUpgrades.size()));
    }
}

