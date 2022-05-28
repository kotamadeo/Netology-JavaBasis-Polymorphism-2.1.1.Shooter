package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class RocketLauncher extends Weapon {
    public RocketLauncher(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_YELLOW + "Бум-бах!" + Utils.ANSI_RESET);
    }
}
