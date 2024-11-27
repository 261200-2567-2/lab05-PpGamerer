public class Assassin extends RPGCharacter implements Job {
    protected String[] abilities = {"Critical Hits", "Stealth", "Evasion"};
    protected boolean criticalHitOn = false;
    protected boolean isStealthed = false;

    public Assassin(String name, int baseRunSpeed) {
        super(name);
        this.maxHP = 100 + (this.level * 5);  // Assassin has lower HP but more mobility
        this.baseRunSpeed = baseRunSpeed;
        this.baseRunSpeed += 3;
        this.currentHP = this.maxHP;
    }
    public Assassin(String name) {
        this(name, 10);
    }

    @Override
    public void useAbility(int choice, RPGCharacter target) {
        switch (choice) {
            case 1:  // Critical Hit
                criticalHit(target);
                break;
            case 2:  // Stealth
                stealth(target);
                break;
            case 3:  // Evasion
                evasion(target);
                break;
            default:
                System.out.println("Invalid ability choice.");
        }
    }

    private void criticalHit(RPGCharacter target) {
        criticalHitOn = true;
        System.out.println(name + " performs a critical hit on " + target.name + " for " + getDamage() + " damage!");
    }

    private void stealth(RPGCharacter target) {
        if (isStealthed) {
            System.out.println(name + " is already in stealth!");
            return;
        }
        isStealthed = true; // Activate stealth.
        System.out.println(name + " is in stealth! Cannot be targeted.");
    }

    private void evasion(RPGCharacter target) {
        double chanceToDodge = Math.random();
        if (chanceToDodge > 0.5) {  // 50
            System.out.println(name + " successfully evades the attack!");
            isStealthed = true;
        } else {
            System.out.println(name + " fails to evade the attack.");
            isStealthed = false;
        }
    }

    @Override
    public String getJobName() {
        return "Assassin";
    }

    @Override
    public String getDescription() {
        return "a character with with mobility and damage abilities, high run speed, but low HP.";
    }

    @Override
    public String[] getAbilities() {
        return abilities;
    }

    @Override
    public boolean canBeTargeted() {
        return !isStealthed;
    }

    @Override
    public void levelUp() {
        level++;
        maxHP = 100 + (level * 8);
        maxMana = 50 + 2 * level;
        currentHP = maxHP;
        currentMana = maxMana;

        System.out.println(name + " leveled up! New level: " + level);
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
        criticalHitOn = false;
        isStealthed = false;
    }

    @Override
    public double getDamage() {
        double netDamage = 0;
        double swordDamage = (sword != null) ? sword.Update() : 0;
        if(criticalHitOn){
            netDamage += swordDamage * 1.5;
        } else {
            netDamage = swordDamage;
        }
        return netDamage;
    }
}
