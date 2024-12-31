package com.engine.model.weapon.upgrade;

import com.engine.model.data.UpgradeWeaponData;
import com.engine.model.weapon.model.AbstractWeapon;

/**
 * Interface représentant un effet d'amélioration pour les armes.
 * <p>
 * Cette interface définit la méthode {@code apply} qui permet d'appliquer un effet spécifique
 * d'amélioration à une arme donnée. Les effets d'amélioration peuvent modifier des propriétés de l'arme,
 * comme les dégâts, la cadence de tir, la portée, etc.
 * </p>
 */
public interface UpgradeEffect {

    /**
     * Applique l'effet d'amélioration sur une arme.
     * <p>
     * Cette méthode permet de modifier les propriétés d'une arme en fonction des données d'amélioration fournies.
     * Par exemple, elle peut augmenter les dégâts d'une arme, ajouter un nouveau comportement de tir ou modifier
     * ses statistiques.
     * </p>
     *
     * @param upgrade les données d'amélioration à appliquer.
     * @param weapon l'arme sur laquelle l'effet d'amélioration sera appliqué.
     */
    void apply(UpgradeWeaponData upgrade, AbstractWeapon weapon);
}
