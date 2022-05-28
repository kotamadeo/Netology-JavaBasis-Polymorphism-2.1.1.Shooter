package com.gmail.at.kotamadeo.program.weapons;

public class Weapon {
    protected String type;
    protected int damage;
    protected String name;

    protected Weapon(String name, String type, int damage) {
        this.name = name;
        this.type = type;
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void smash() {
        System.out.println("Удар");
    }

    public void shot() {
        System.out.println("Выстрел");
    }

    @Override
    public String toString() {
        return name + " тип: " + type + " урон: " + damage;
    }
}
