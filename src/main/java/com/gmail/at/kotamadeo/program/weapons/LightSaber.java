package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class LightSaber extends Weapon {
    public LightSaber(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void smash() {
        System.out.println(Utils.ANSI_PURPLE + "Шврммммммм!" + Utils.ANSI_RESET);
    }
}
