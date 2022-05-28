package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class PawPaw extends Weapon {
    public PawPaw(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_WHITE + "Пау-пау!" + Utils.ANSI_RESET);
    }
}
