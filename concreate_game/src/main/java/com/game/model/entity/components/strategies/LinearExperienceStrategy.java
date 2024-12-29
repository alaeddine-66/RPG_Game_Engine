package com.game.model.entity.components.strategies;


import com.engine.model.entity.components.strategies.IExperienceCalculationStrategy;

public class LinearExperienceStrategy implements IExperienceCalculationStrategy {
    @Override
    public int calculateMaxExp(int level, int previousMaxExp) {
        return level * 100; // Simple formule linéaire
    }
}

