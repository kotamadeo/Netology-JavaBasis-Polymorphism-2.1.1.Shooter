package com.gmail.at.kotamadeo.program.monsters;

import com.gmail.at.kotamadeo.utils.Utils;

public class Demon extends Monster{
    public Demon(String name, int damage, int hp) {
        super(name, damage, hp);
    }

    @Override
    public void attack() {
        System.out.println(Utils.ANSI_RED + "Бросок огненного шара!" + Utils.ANSI_RESET);
    }
}
