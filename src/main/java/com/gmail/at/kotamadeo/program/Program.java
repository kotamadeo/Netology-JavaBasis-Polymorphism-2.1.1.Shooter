package com.gmail.at.kotamadeo.program;

import com.gmail.at.kotamadeo.utils.Utils;

import java.util.Scanner;

public class Program {
    private final Scanner scanner = new Scanner(System.in);
    private String name;
    private int hpPlayer;
    private int playerDamage;
    private final MonstersPack monstersPack = new MonstersPack();

    public void start() {
        try {
            String input;
            System.out.println(Utils.ANSI_BLUE + "Чтобы создать игрока введите имя и уровень здоровья через пробел:");
            var allInput = scanner.nextLine().split(" ", 2);
            name = allInput[0];
            hpPlayer = Integer.parseInt(allInput[1]);
            Player player = new Player(name, hpPlayer);
            while (true) {
                try {
                    printMenu();
                    input = scanner.nextLine();
                    if ("выход".equalsIgnoreCase(input) || "0".equals(input)) {
                        scanner.close();
                        break;
                    } else {
                        var operationNumber = Integer.parseInt(input);
                        switch (operationNumber) {
                            case 1:
                                System.out.println(Utils.ANSI_BLUE + "Выберите оружие из имеющегося списка:" +
                                        Utils.ANSI_RESET);
                                player.listWeapon();
                                input = scanner.nextLine();
                                player.shotWithWeapon(Integer.parseInt(input) - 1);
                                playerDamage = player.getDamage();
                                break;
                            case 2:
                                System.out.println(Utils.ANSI_BLUE + "Выберите монстра из имеющегося списка:" +
                                        Utils.ANSI_RESET);
                                monstersPack.monstersList();
                                input = scanner.nextLine();
                                monstersPack.setChosenMonster(Integer.parseInt(input) - 1);
                                monstersPack.monsterAttack();
                                break;
                            case 3:
                                System.out.println(Utils.ANSI_GREEN + "Начинаем бой!" + Utils.ANSI_RESET);
                                battle(monstersPack.getHp());
                                break;
                            default:
                                System.out.println(Utils.ANSI_RED + "Вы ввели неверный номер операции!" +
                                        Utils.ANSI_RESET);
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println(Utils.ANSI_RED + "Ошибка ввода данных!" + Utils.ANSI_RESET);
                }
            }
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            System.out.println(Utils.ANSI_RED + "Возможно вы вводите данные не через пробел!" + Utils.ANSI_RESET);
            start();
        }
    }

    private static void printMenu() {
        System.out.println(Utils.ANSI_GREEN + "Эта игра позволяет бороться с захватчиками." + Utils.ANSI_RESET);
        System.out.println(Utils.ANSI_YELLOW + "Возможные команды:" + Utils.ANSI_RESET);
        System.out.println("0 или выход: выход из программы.");
        System.out.println("1: вооружиться и произвести учебный выстрел.");
        System.out.println("2: выбрать монстра и произвести демонстрацию атаки.");
        System.out.println("3: устроить бой.");
        System.out.print(">>>>");
    }

    private void battle(int monsterHP) {
        var roundCounter = 0;
        while (true) {
            roundCounter++;
            if (hpPlayer > 0) {
                hpPlayer -= monstersPack.getDamage();
                if (hpPlayer < 0) {
                    hpPlayer = 0;
                }
                System.out.println(Utils.ANSI_PURPLE + "Раунд - " + roundCounter + ":" + Utils.ANSI_RESET);
                System.out.printf("%s получил %s урона. Осталось здоровья: %s.%n", name, monstersPack.getDamage(),
                        hpPlayer);
                Utils.printDelim();
            }
            if (monsterHP > 0) {
                monsterHP -= playerDamage;
                if (monsterHP < 0) {
                    monsterHP = 0;
                }
                System.out.printf("Монстр получил %s урона. Осталось здоровья: %s.%n", playerDamage, monsterHP);
                Utils.printDelim();
                System.out.println();
            }
            if (hpPlayer <= 0 || monsterHP <= 0) {
                System.out.println(isPlayerWin(hpPlayer, monsterHP) ? ("Победил " + name + "!") : "Победил монстр!");
                break;
            }
        }
    }

    private static boolean isPlayerWin(int hpPlayer, int monsterHP) {
        return hpPlayer > monsterHP;
    }
}
