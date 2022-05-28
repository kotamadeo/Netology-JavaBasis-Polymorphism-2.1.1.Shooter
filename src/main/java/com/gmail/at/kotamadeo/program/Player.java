package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.program.weapons.AntimatterGun;
import com.gmail.at.kotamadeo.program.weapons.Blaster;
import com.gmail.at.kotamadeo.program.weapons.Bowcaster;
import com.gmail.at.kotamadeo.program.weapons.Chainsaw;
import com.gmail.at.kotamadeo.program.weapons.Fists;
import com.gmail.at.kotamadeo.program.weapons.LightSaber;
import com.gmail.at.kotamadeo.program.weapons.PawPaw;
import com.gmail.at.kotamadeo.program.weapons.PlasmaRifle;
import com.gmail.at.kotamadeo.program.weapons.RocketLauncher;
import com.gmail.at.kotamadeo.program.weapons.Spear;
import com.gmail.at.kotamadeo.program.weapons.Weapon;
import com.gmail.at.kotamadeo.utils.Utils;

public class Player {
    private final Weapon[] weaponSlots;
    private String name;
    private int hp;
    private int damage;
    private static final int MIN_HP = 60;
    private static final int MAX_HP = 999;

    public Player(String name, int hp) {
        this.name = name;
        if (hp > MAX_HP) {
            System.out.printf("%sМда, честности вам не занимать! Ваш уровень здоровья установлен на уровне %s единиц." +
                    "%s%n", Utils.ANSI_RED, MIN_HP, Utils.ANSI_RESET);
            this.hp = MIN_HP;
        } else if (hp < MIN_HP) {
            System.out.printf("%sИзлишняя хардкорность не требуется! Уровень вашего здоровья установлен на уровне " +
                    "%s единиц.%s%n", Utils.ANSI_GREEN, MIN_HP, Utils.ANSI_RESET);
            this.hp = MIN_HP;
        } else {
            this.hp = hp;
        }
        weaponSlots = new Weapon[]{
                new AntimatterGun("Пушка-антиматерии", "дальний бой", 99999),
                new Blaster("Бластер", "дальний бой", 50),
                new Bowcaster("Боукастер", "дальний бой", 90),
                new Chainsaw("Бензопила", "ближний бой", 99),
                new Fists("Кулаки", "ближний бой", 20),
                new LightSaber("Световой меч", "ближний бой", 99),
                new PawPaw("Пулемет Пыщ-Пыщ", "дальний бой", 85),
                new PlasmaRifle("Плазма-пушка", "дальний бой", 80),
                new RocketLauncher("Ракетная установка", "дальний бой", 100),
                new Spear("Копье", "ближний бой", 25)};
    }

    public void shotWithWeapon(int slot) {
        if (slot < 0 || slot > weaponSlots.length) {
            System.out.println(Utils.ANSI_RED + "Вы вышли за границы выбора! Оружие под номером " + slot +
                    " не существует!" + Utils.ANSI_RESET);
        } else {
            var weapon = weaponSlots[slot];
            if ("дальний бой".equalsIgnoreCase(weapon.getType())) {
                System.out.printf("%sСтреляем из %s%s: ", Utils.ANSI_CYAN, weapon.getName(), Utils.ANSI_RESET);
                weapon.shot();
            } else {
                System.out.printf("%sАтакуем с помощью %s%s: ", Utils.ANSI_CYAN, weapon.getName(), Utils.ANSI_RESET);
                weapon.smash();
            }
            damage = weapon.getDamage();
        }
    }

    public void listWeapon() {
        System.out.println("Доступное количество оружия: " + weaponSlots.length);
        for (var i = 0; i < weaponSlots.length; i++) {
            System.out.printf("%s%s.%s.%s%n", Utils.ANSI_PURPLE, i + 1, weaponSlots[i], Utils.ANSI_RESET);
        }
    }

    public int getDamage() {
        return damage;
    }
}
