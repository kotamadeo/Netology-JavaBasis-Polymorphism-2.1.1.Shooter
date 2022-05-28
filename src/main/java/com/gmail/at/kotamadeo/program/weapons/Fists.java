package com.gmail.at.kotamadeo.program.weapons;

public class Fists extends Weapon {
    public Fists(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void smash() {
        System.out.println("*Звуки ударов*");
    }
}
