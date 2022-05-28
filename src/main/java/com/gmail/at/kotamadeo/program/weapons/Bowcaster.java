package com.gmail.at.kotamadeo.program.weapons;

import com.gmail.at.kotamadeo.utils.Utils;

public class Bowcaster extends Weapon {
    public Bowcaster(String name, String type, int damage) {
        super(name, type, damage);
    }

    @Override
    public void shot() {
        System.out.println(Utils.ANSI_GREEN + "Поуууу!" + Utils.ANSI_RESET);
    }
}
