public class Lab05 {
    public static void main(String[] args) {
        Assassin hero = new Assassin("Hero");
        Sword sword = new Sword("Diamond Sword", 1, 40);
        Shield shield = new Shield("Wooden Shield", 1, 10);

        Priest villain = new Priest ("Villain");

        Boot boots = new Boot("Speedy Boots",2);
        Necklace necklace = new Necklace("Prayer Beads", 3);

        hero.Status();
        villain.Status();

        hero.equipAccessory(boots);
        hero.equipAccessory(necklace);
        villain.equipAccessory(boots);
        System.out.println("");
        hero.Status();
        villain.Status();
        System.out.println("");
        hero.equipSword(sword);
        hero.equipShield(shield);
        System.out.println("");
        hero.useAbility(1,hero);
        hero.Status();

        boots.levelUp();

        hero.attack(villain);
        villain.Status();
        System.out.println("");

        hero.levelUp();
        hero.Status();
        villain.useAbility(3, villain);
        villain.useAbility(1, villain);
        villain.Status();
        sword.levelUp();
        shield.levelUp();
        hero.Status();
        villain.equipShield(shield);
        villain.levelUp();
        villain.equipSword(sword);
        villain.Status();

        hero.attack(villain);
        villain.attack(hero);
        hero.useAbility(2,hero);
        villain.attack(hero);
        hero.attack(villain);
        villain.Status();
        villain.attack(hero);
        villain.attack(hero);
        hero.Status();
        villain.attack(hero);
        villain.useAbility(2,hero);
        hero.Status();

        hero.printDescription();
        hero.printAbilities();

        villain.printDescription();
        villain.printAbilities();
    }
}