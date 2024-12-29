package com.game.model.entity.components.strategies;

import com.engine.model.entity.components.strategies.IExperienceCalculationStrategy;

public class ExponentialExperienceStrategy implements IExperienceCalculationStrategy {
    @Override
    public int calculateMaxExp(int level, int previousMaxExp) {
        return (int) (5 * level * Math.pow(2, level )) + previousMaxExp;
    }
}
