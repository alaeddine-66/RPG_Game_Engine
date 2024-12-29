package com.engine.model.data;

import java.util.HashMap;

public class EnemyData {
    private String id;
    private String Type;
    private String idlePath;
    private String movePath;
    private String attackPath;
    private int width;
    private int height ;
    private int damage;
    private int maxHp;
    private int speed;
    private HashMap<String,Integer> drops;
    private HashMap<String, Integer> extra; // Use HashMap for LibGDX compatibility


    // Getters and Setters
    public String getId() {
        return id;
    }

    public String getIdlePath() {
        return idlePath;
    }

    public String getMovePath() {
        return movePath;
    }

    public String getAttackPath() {
        return attackPath;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    public int getDamage() {
        return damage;
    }

    public int getmaxHp() {
        return maxHp;
    }

    public int getSpeed() {
        return speed;
    }

    public HashMap<String, Integer> getDrops() {
        return drops;
    }

    public String getType() {
        return Type;
    }

    @Override
    public String toString() {
        return "EnemyData {" + "id='" + id  + "}";
    }

    public HashMap<String, Integer> getExtra() {
        return extra;
    }
}
