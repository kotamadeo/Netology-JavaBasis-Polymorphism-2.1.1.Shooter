package com.gmail.at.kotamadeo.program.monsters;

import com.gmail.at.kotamadeo.utils.Utils;

public class Undead extends Monster{
    public Undead(String name, int damage, int hp) {
        super(name, damage, hp);
    }

    @Override
    public void attack() {
        System.out.println(Utils.ANSI_CYAN + "Нападение легиона мертвецов!" + Utils.ANSI_RESET);
    }
}
