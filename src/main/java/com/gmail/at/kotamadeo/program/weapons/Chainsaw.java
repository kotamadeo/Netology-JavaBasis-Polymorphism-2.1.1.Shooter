package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class Chainsaw extends Weapon {
    public Chainsaw(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void smash() {
        System.out.println(Utils.ANSI_RED + "Врум-врум!" + Utils.ANSI_RESET);
    }
}
