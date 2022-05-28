package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class AntimatterGun extends Weapon {
    public AntimatterGun(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_RED + "*Звуки аннигиляции*" + Utils.ANSI_RESET);
    }
}
