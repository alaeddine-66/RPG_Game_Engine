package com.engine.model.weapon.upgrade;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.resource.DataManager;
import com.engine.model.weapon.model.AbstractWeapon;


public class UpgradeManager {

    private List<UpgradeWeaponData> availableUpgrades;
    private Map<String, UpgradeEffect> effectMap;

    public void loadUpgrades(String filePath) {
        UpgradeDataList upgradeDataList = DataManager.loadJsonData(filePath, UpgradeDataList.class);
        this.availableUpgrades = upgradeDataList.getUpgrades();
        effectMap = new HashMap<>();
    }

    // Méthode pour ajouter un effet personnalisé par l'utilisateur
    public void addEffect(String effectName, UpgradeEffect effect) {
        effectMap.put(effectName, effect);
    }

    public UpgradeEffect getEffect(String name) {
        return effectMap.get(name);
    }

    public void applyUpgrade(UpgradeWeaponData upgrade, AbstractWeapon weapon) {
        UpgradeEffect effect = getEffect(upgrade.getEffect());
        effect.apply(upgrade, weapon);
    }

    /**
     * Sélectionne aléatoirement une amélioration parmi les améliorations disponibles.
     *
     * @return Une {@code UpgradeWeaponData} choisie aléatoirement.
     */
    public UpgradeWeaponData getRandomUpgrade() {
        return availableUpgrades.get((int) (Math.random() * availableUpgrades.size()));
    }
}

