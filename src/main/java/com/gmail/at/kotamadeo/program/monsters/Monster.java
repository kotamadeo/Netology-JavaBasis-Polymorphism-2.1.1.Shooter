package com.gmail.at.kotamadeo.program.monsters;

import com.gmail.at.kotamadeo.utils.Utils;

public class Monster {
    protected String name;
    protected int damage;
    protected int hp;

    protected Monster(String name, int damage, int hp) {
        this.name = name;
        this.damage = damage;
        this.hp = hp;
    }

    public void attack() {
        System.out.println("Атака");
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return name + " урон: " + damage + " здоровье: " + hp;
    }
}
