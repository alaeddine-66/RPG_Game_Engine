package com.engine.model.data;

public class PlayerData {
    private String id;
    private Position startPosition;
    private int width;
    private int height;
    private Images images;
    private String StartWeapon;
    private int Strength ;
    private int speed;
    private int maxHp;
    private int maxExp;
    private int maxMp;
    private int PLAYER_MIN_DMG_INCREASE ;
    private int PLAYER_MAX_DMG_INCREASE ;
    private int PLAYER_MIN_HP_INCREASE ;
    private int PLAYER_MAX_HP_INCREASE ;

    public String getStartWeapon() {
        return StartWeapon;
    }


    public static class Position {
        private int x, y;

        // Getters et Setters pour Position
        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }


    }

    public static class Images {
        private String idle_g , idle_d;

        // Getters et Setters pour Position
        public String getidleG() {
            return idle_g;
        }

        public String getidleD() {
            return idle_d;
        }

    }

    // Getters et Setters pour PlayerData
    public String getid() {
        return id;
    }

    public Images getimages(){
        return images;
    }

    public Position getStartPosition() {
        return startPosition;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getStrength() {
        return Strength;
    }

    public int getSpeed() {
        return speed;
    }

    public int getmaxHp() {
        return maxHp;
    }
    public int getmaxExp(){
        return maxExp;
    }

    public int getmaxMp(){
        return maxMp;
    }

    public int getMinHpIncrease() {
        return PLAYER_MIN_HP_INCREASE;
    }

    public void setMinHpIncrease(int PLAYER_MIN_HP_INCREASE) {
        this.PLAYER_MIN_HP_INCREASE = PLAYER_MIN_HP_INCREASE;
    }

    public int getMinDmgIncrease() {
        return PLAYER_MIN_DMG_INCREASE;
    }

    public void setMinDmgIncrease(int PLAYER_MIN_DMG_INCREASE) {
        this.PLAYER_MIN_DMG_INCREASE = PLAYER_MIN_DMG_INCREASE;
    }

    public int getMaxDmgIncrease() {
        return PLAYER_MAX_DMG_INCREASE;
    }

    public void setMaxDmgIncrease(int PLAYER_MAX_DMG_INCREASE) {
        this.PLAYER_MAX_DMG_INCREASE = PLAYER_MAX_DMG_INCREASE;
    }

    public int getMaxHpIncrease() {
        return PLAYER_MAX_HP_INCREASE;
    }

    public void setMaxHpIncrease(int PLAYER_MAX_HP_INCREASE) {
        this.PLAYER_MAX_HP_INCREASE = PLAYER_MAX_HP_INCREASE;
    }

}
