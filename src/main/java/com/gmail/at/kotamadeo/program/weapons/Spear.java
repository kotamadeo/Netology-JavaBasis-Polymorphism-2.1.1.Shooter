package com.gmail.at.kotamadeo.program.weapons;

public class Spear extends Weapon {
    public Spear(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void smash() {
        System.out.println("Крррр!");
    }
}

