# **Задачи № 1 Шутер**

## **Цель**:
Один из проектов — это игра-шутер (~~Half-Life 3, только никому ни слова~~). У игрока должна быть возможность использовать разные виды оружия, в будущем в игру могут быть добавлены новые. Необходимо спроектировать иерархию классов, а также систему слотов для оружия у игрока.
1. Создать объект ```Player``` у которого будет набор оружия;
2. Реализовать возможность у игрока вызвать метод выстрела, внутри которого будут проверки на допустимость номера оружия для выстрела;
3. Классы оружия должны быть в пакете *weapon* (вспомните какие ДВЕ вещи нужно сделать, чтобы поместить классы в пакеты; мы их проходили на втором занятии);
4. Реализовать возможность выбора оружия для выстрела в **Main**.

### *Пример*:

``` Пример 1
Чтобы создать игрока введите имя и уровень здоровья через пробел:
Думгай 320

Эта игра позволяет бороться с захватчиками.
Возможные команды:
0 или выход: выход из программы.
1: вооружиться и произвести учебный выстрел.
2: выбрать монстра и произвести демонстрацию атаки.
3: устроить бой.
>>>>
1
Выберите оружие из имеющегося списка:
Доступное количество оружия: 10
1.Пушка-антиматерии тип: дальний бой урон: 99999.
2.Бластер тип: дальний бой урон: 50.
3.Боукастер тип: дальний бой урон: 90.
4.Бензопила тип: ближний бой урон: 99.
5.Кулаки тип: ближний бой урон: 20.
6.Световой меч тип: ближний бой урон: 99.
7.Пулемет Пыщ-Пыщ тип: дальний бой урон: 85.
8.Плазма-пушка тип: дальний бой урон: 80.
9.Ракетная установка тип: дальний бой урон: 100.
10.Копье тип: ближний бой урон: 25.
10
Атакуем с помощью Копье: Крррр!

2
Выберите монстра из имеющегося списка:
Доступное количество монстров: 3
1.Демон урон: 60 здоровье: 120.
2.Зомби урон: 30 здоровье: 99.
3.Нежить урон: 75 здоровье: 320.
3
Выбранный монстр: Нежить.
Нападение легиона мертвецов!

3
Начинаем бой!
Раунд - 1:
Думгай получил 75 урона. Осталось здоровья: 245.
*********************************************
Монстр получил 25 урона. Осталось здоровья: 295.
*********************************************

Раунд - 2:
Думгай получил 75 урона. Осталось здоровья: 170.
*********************************************
Монстр получил 25 урона. Осталось здоровья: 270.
*********************************************

Раунд - 3:
Думгай получил 75 урона. Осталось здоровья: 95.
*********************************************
Монстр получил 25 урона. Осталось здоровья: 245.
*********************************************

Раунд - 4:
Думгай получил 75 урона. Осталось здоровья: 20.
*********************************************
Монстр получил 25 урона. Осталось здоровья: 220.
*********************************************

Раунд - 5:
Думгай получил 75 урона. Осталось здоровья: 0.
*********************************************
Монстр получил 25 урона. Осталось здоровья: 195.
*********************************************

Победил монстр!
```

### **Моя реализация**:

1. Реализация осуществлена в парадигме ООП.
2. Создал структуру классов:

* **Program** - отвечающий за запуск программы, путем инициирования метода *start()* с инициированием внутри себя
  вспомогательных методов: 
  * *printMenu()* - выводит меню на экран; 
  * *battle()* - инициирует бой между игроком и монстром; 
  * *isPlayerWin()* - проверяет победил ли игрок.

#### Класс **Program**:
``` java
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
```

* **Weapon** - описывающий работу оружия и являющийся суперклассом для классов: **AntimatterGun, Blaster, Bowcaster, Chainsaw, Fists, Lightsaber, PawPaw, PlasmaRifle, RocketLauncer и Spear**. 
Имеет следующие важные ```void``` методы: *smash()*, который моделирует удар и *shot()*, который моделирует выстрел. Так же класс имеет переопределенный метод *toString()* и геттер-методы для получения значений следующих полей: type, damage, name;

#### Класс **Weapon**:
``` java   
public class Weapon {
    protected String type;
    protected int damage;
    protected String name;

    protected Weapon(String name, String type, int damage) {
        this.name = name;
        this.type = type;
        this.damage = damage;
    }

    public String getType() {
        return type;
    }

    public int getDamage() {
        return damage;
    }

    public String getName() {
        return name;
    }

    public void smash() {
        System.out.println("Удар");
    }

    public void shot() {
        System.out.println("Выстрел");
    }

    @Override
    public String toString() {
        return name + " тип: " + type + " урон: " + damage;
    }
}
```
* **AntimatterGun** - реализующий пушку-антиматерии посредством наследования и переопределения методов **Weapon**;
* **Blaster** - реализующий бластер посредством наследования и переопределения методов **Weapon**;
* **Bowcaster** - реализующий боукастер посредством наследования и переопределения методов **Weapon**;
* **Chainsaw** - реализующий бензопилу посредством наследования и переопределения методов **Weapon**;
* **Fists** - реализующий кулаки посредством наследования и переопределения методов **Weapon**;
* **LightSaber** - реализующий световой меч посредством наследования и переопределения методов **Weapon**;
* **PawPaw** - реализующий пулемет "Пыщ-Пыщ" посредством наследования и переопределения методов **Weapon**;
* **PlasmaRifle** - реализующий плазма-пушку посредством наследования и переопределения методов **Weapon**;
* **RocketLauncher** - реализующий ракетную установку посредством наследования и переопределения методов **Weapon**;
* **Spear** - реализующий копье посредством наследования и переопределения методов **Weapon**;
* **Player** - описывающий игрока. Имеет ```void``` методы *shotWithWeapon()*, позволяющий совершить атаку выбранным оружием и *listWeapon()*, выводящий список оружия, которым может вооружиться игрок, так же имеет геттер-метод  *getDamage()*;

#### Класс **Player**:
``` java   
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
```

* **Monster** - описывающий монстра, и являющийся суперклассом для классов: **Demon, Zombie и Undead**. Имеет следующий важный ```void``` метод: *attack()*, который моделирует атаку. Так же класс имеет переопределенный метод *toString()* и геттер-методы для получения значений следующих полей: hp, damage, name;

#### Класс **Monster**:
``` java   
public class Monster {
    protected Utils utils = new Utils();
    protected String name;
    protected int damage;
    protected int hp;

    protected Monster(String name, int damage, int hp) {
        this.name = name;
        this.damage = damage;
        this.hp = hp;
    }

    public void attack() {
        System.out.println("Атака");
    }

    public String getName() {
        return name;
    }

    public int getHp() {
        return hp;
    }

    public int getDamage() {
        return damage;
    }

    @Override
    public String toString() {
        return name + " урон: " + damage + " здоровье: " + hp;
    }
}
```

* **Demon** - описывающий демона посредством наследования и переопределения метода **Monster**;
* **Zombie** - описывающий зомби посредством наследования и переопределения метода **Monster**;
* **Undead** - описывающий нежить посредством наследования и переопределения метода **Monster**;
* **MonsterPack** - описывающий стаю монстров, по сути класс - антагонист **Player**. Имеет ```void``` методы: *monstersList()*, выводящий список монстров доступных для выбора, *setChosenMonster()*, позволяющий выбрать монстра и **monsterAttack()*, позволяющий выбранному монстру атаковать.

#### Класс **MonstersPack**:
``` java   
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
```

2. Использовал кодирование цвета текста (ANSI).

#### Класс **Utils**:
``` java
public class Utils {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static void printDelim() {
        System.out.println(ANSI_GREEN + "*********************************************" + ANSI_RESET);
    }
}
```

3. Использовал ```try-catch```, чтобы избежать падение программы в исключения.

#### Метод *main()* в классе **Main**:
``` java
public class Main {
    public static void main(String[] args) {
        var program = new Program();
        program.start();
    }
}
```

## *Вывод в консоль*:

* меню:
``` 
Эта игра позволяет бороться с захватчиками.
Возможные команды:
0 или выход: выход из программы.
1: вооружиться и произвести учебный выстрел.
2: выбрать монстра и произвести демонстрацию атаки.
3: устроить бой.
>>>>
```