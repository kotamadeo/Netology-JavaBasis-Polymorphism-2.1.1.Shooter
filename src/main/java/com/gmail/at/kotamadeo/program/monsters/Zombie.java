package com.gmail.at.kotamadeo.program.monsters;

import com.gmail.at.kotamadeo.utils.Utils;

public class Zombie extends Monster {
    public Zombie(String name, int damage, int hp) {
        super(name, damage, hp);
    }

    @Override
    public void attack() {
        System.out.println(Utils.ANSI_BLACK + "Высасывание мозгов!" + Utils.ANSI_RESET);
    }
}
