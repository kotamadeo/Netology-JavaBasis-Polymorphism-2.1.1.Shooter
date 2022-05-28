package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class PlasmaRifle extends Weapon {
    public PlasmaRifle(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_GREEN + "Пиу-Пау-Пиу!" + Utils.ANSI_RESET);
    }
}
