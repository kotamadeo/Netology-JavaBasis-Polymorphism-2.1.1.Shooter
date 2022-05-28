package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.program.monsters.Demon;
import com.gmail.at.kotamadeo.program.monsters.Monster;
import com.gmail.at.kotamadeo.program.monsters.Undead;
import com.gmail.at.kotamadeo.program.monsters.Zombie;
import com.gmail.at.kotamadeo.utils.Utils;

public class MonstersPack {
    private Monster chosenMonster;
    private final Monster[] monster;
    private int hp;
    private int damage;

    MonstersPack() {
        monster = new Monster[]{
                new Demon("Демон", 60, 120),
                new Zombie("Зомби", 30, 99),
                new Undead("Нежить", 75, 320)
        };
    }

    public void monstersList() {
        System.out.println("Доступное количество монстров: " + monster.length);
        for (var i = 0; i < monster.length; i++) {
            System.out.printf("%s%s.%s.%s%n", Utils.ANSI_PURPLE, i + 1, monster[i], Utils.ANSI_RESET);
        }
    }

    public void setChosenMonster(int input) {
        chosenMonster = monster[input];
        System.out.println("Выбранный монстр: " + chosenMonster.getName() + ".");
        hp = chosenMonster.getHp();
        damage = chosenMonster.getDamage();
    }

    public void monsterAttack() {
        chosenMonster.attack();
    }

    public int getDamage() {
        return damage;
    }

    public int getHp() {
        return hp;
    }
}
