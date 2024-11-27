public class Priest extends RPGCharacter implements Job {
    protected String[] abilities = {"Heal", "Revive", "Blessing"};
    protected boolean blessingOn = false;

    public Priest(String name, int baseRunSpeed) {
        super(name, 8);
        this.baseRunSpeed = baseRunSpeed;
//        this.baseRunSpeed -= 2;
        this.currentHP = this.maxHP;
        this.currentMana = 50 + (this.level * 5);
        this.maxMana = this.currentMana;
    }
    public Priest(String name) {
        this(name, 8);
    }

    @Override
    public void useAbility(int choice, RPGCharacter target) {
        switch (choice) {
            case 1:  // Heal
                heal(target);
                break;
            case 2:  // Revive
                revive(target);
                break;
            case 3:  // Blessing
                blessing(target);
                break;
            default:
                System.out.println("Invalid ability choice.");
        }
    }

    private void heal(RPGCharacter target) {
        double healAmount = 20 + (this.level * 2);
        target.currentHP += healAmount;
        System.out.println(name + " heals " + target.name + " for " + healAmount + " HP!");
    }

    private void revive(RPGCharacter target) {
        if (target.currentHP == 0) {
            target.currentHP = (target.maxHP * 0.3);  // Revives with 30% HP
            System.out.println(name + " revives " + target.name + "!");
        } else {
            System.out.println(target.name + " is already alive.");
        }
    }

    private void blessing(RPGCharacter target) {
        blessingOn = true;
        System.out.println(name + " blesses " + target.name + " with increased damage and defense!");
    }

    @Override
    public String getJobName() {
        return "Priest";
    }

    @Override
    public String getDescription() {
        return "a character with healing and buffing abilities, high mana, but low run speed.";
    }

    @Override
    public String[] getAbilities() {
        return abilities;
    }

    @Override
    public void levelUp() {
        level++;
        maxHP = 100 + 10 * level;
        maxMana = 50 + (this.level * 5);
        currentHP = maxHP;

        double mana = maxMana;
        for (Accessory accessory : accessories) {
            if (accessory instanceof Mana) {
                mana += ((Mana) accessory).getManaBoost();
            }
        }

        currentMana = mana;

        System.out.println(name + " leveled up! New level: " + level);
    }

    @Override
    public double getDamage() {
        double netDamage = 0;
        double swordDamage = (sword != null) ? sword.Update() : 0;
        if(blessingOn){
            netDamage = swordDamage + (3*level);
        } else {
            netDamage = swordDamage;
        }
        return netDamage;
    }

    @Override
    public double getDefense() {
        double netDefense = 0;
        double shieldDefense = (shield != null) ? shield.Update() : 0;
        if(blessingOn){
            netDefense = shieldDefense + (3*level);
        }
        return netDefense;
    }

    @Override
    public void attack(RPGCharacter target) {
        if(!target.canBeTargeted()) {
            System.out.println(target.name + " cannot be attacked.");
            return;
        }
        double damageDealt = getDamage();
        double defense = target.getDefense();

        double netDamage = Math.max(0, damageDealt - defense);

        target.currentHP -= netDamage;

        System.out.println(name + " attacks " + target.name + " for " + netDamage + " damage!");
        if(target.currentHP < 0) {
            target.currentHP = 0;
        }
        System.out.println(target.name + "'s remaining HP: " + target.currentHP);
        blessingOn = false;
    }

//    @Override
//    public boolean canBeTargeted() {
//        return true;
//    }
}
