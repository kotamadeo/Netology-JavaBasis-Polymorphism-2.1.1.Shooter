package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class Blaster extends Weapon {
    public Blaster(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_BLUE + "Пиу-пиу!" + Utils.ANSI_RESET);
    }
}
