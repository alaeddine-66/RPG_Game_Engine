package com.engine.model.weapon.upgrade;

import java.util.List;
import com.engine.model.data.UpgradeWeaponData ;

/**
 * Classe représentant une liste d'améliorations d'armes.
 * Cette classe contient une collection d'objets {@code UpgradeWeaponData},
 * qui définissent les améliorations possibles pour les armes dans le jeu.
 */
public class UpgradeDataList {

    /**
     * Liste des améliorations disponibles pour les armes.
     */
    private List<UpgradeWeaponData> upgrades;

    /**
     * Récupère la liste des améliorations d'armes.
     *
     * @return Une {@code List} contenant les améliorations d'armes sous forme d'objets {@code UpgradeWeaponData}.
     */
    public List<UpgradeWeaponData> getUpgrades() {
        return upgrades;
    }

    /**
     * Définit la liste des améliorations d'armes.
     *
     * @param upgrades Une {@code List} d'objets {@code UpgradeWeaponData} représentant les nouvelles améliorations.
     */
    public void setUpgrades(List<UpgradeWeaponData> upgrades) {
        this.upgrades = upgrades;
    }
}
