import java.util.ArrayList;
import java.util.List;

public abstract class RPGCharacter implements Job{
    protected String name;
    protected int level;
    protected double maxHP;
    protected double maxMana;
    protected double currentHP;
    protected double currentMana;
    protected int baseRunSpeed;
    protected Sword sword;
    protected Shield shield;
    protected List<Accessory> accessories;

    public RPGCharacter(String name, int baseRunSpeed) {
        this.name = name;
        this.level = 1;
        this.maxHP = 100 + 10 * level;
        this.maxMana = 50 + 2 * level;
        this.currentHP = maxHP;
        this.currentMana = maxMana;
        this.baseRunSpeed = baseRunSpeed;
        this.sword = null;
        this.shield = null;
        this.accessories = new ArrayList<>();
    }

    public RPGCharacter(String name) {
        this(name, 10);
    }

    public void equipSword(Sword sword) {
        this.sword = sword;
        System.out.println(sword.getName() + " equipped to " + name + "!");
    }

    public void equipShield(Shield shield) {
        this.shield = shield;
        System.out.println(shield.getName() + " equipped to " + name + "!");
    }

    public void equipAccessory(Accessory accessory) {
        if (accessory == null) {
            System.out.println("Cannot equip a null accessory!");
            return;
        }

        for (Accessory equipped : accessories) {
            if (equipped.getClass().equals(accessory.getClass())) {
                System.out.println("Cannot equip another " + accessory.getType());
                return;
            }
        }

        accessories.add(accessory);
        System.out.println(accessory.getName() + " equipped to " + name + "!");
    }

    public double getDamage() {
        double swordDamage = (sword != null) ? sword.Update() : 0;
        return swordDamage;
    }

    public double getDefense() {
        double shieldDefense = (shield != null) ? shield.Update() : 0;
        return shieldDefense;
    }

    public double getRunSpeed() {
        double runSpeed = baseRunSpeed;
        // Apply speed boosts from all accessories
        for (Accessory accessory : accessories) {
            if (accessory.getType().equals("Movement")) {
                if(accessory.getLevel() == 1) {
                    runSpeed += ((Movement) accessory).getSpeedBoost();
                } else {
                    runSpeed += accessory.Update();
                }
            }
        }
        double swordSpeedEffect = 0;
        double shieldSpeedEffect = 0;
        if(sword != null){
            swordSpeedEffect = baseRunSpeed * (0.1 + 0.04 * sword.getLevel());
        }
        if(shield != null){
            shieldSpeedEffect = baseRunSpeed * (0.1 + 0.08 * shield.getLevel());
        }
        runSpeed = (runSpeed - swordSpeedEffect - shieldSpeedEffect);
        double maxRunSpeed = baseRunSpeed + 3 + (baseRunSpeed * (0.1 + 0.03 * level));
        if (maxRunSpeed < 1) {
            maxRunSpeed = 1; //Minimum
        }
        if (runSpeed < 1) {
            return 1; //Minimum
        } else if (runSpeed > maxRunSpeed) {
            System.out.println(name + " has reached the maximum run speed!");
            return maxRunSpeed; //Maximum
        }
        return runSpeed;
    }

    public double getMana() {
        double mana = currentMana;
        // Apply mana boosts from accessories
        for (Accessory accessory : accessories) {
            if (accessory.getType().equals("Mana")) {
                mana += accessory.Update();  // Increase mana based on necklace
            }
        }
        return mana;
    }

    public double getMaxMana() {
        double mana = maxMana;
        for (Accessory equipped : accessories) {
            if(equipped.getType().equals("Mana")){
                mana += equipped.Update();
            }
        }
        return mana;
    }

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
    }

    public void Status() {
        System.out.println(name + " (Level: " + level + ")");
        System.out.println("HP: " + currentHP + "/" + maxHP + ", Mana: " + getMana() + "/" + getMaxMana());
        System.out.println("Damage: " + getDamage() + ", Defense: " + getDefense());
        System.out.println("Run Speed: " + getRunSpeed());

        if (sword != null) {
            sword.Status();
        }

        if (shield != null) {
            shield.Status();
        }

        for (Accessory accessory : accessories) {
            accessory.Status();
        }
    }

    public void levelUp() {
        level++;
        maxHP = 100 + 10 * level;
        maxMana = 50 + 2 * level;
        currentHP = maxHP;
        currentMana = maxMana;

        System.out.println(name + " leveled up! New level: " + level);
    }

    public boolean canBeTargeted(){
        return true;
    }
}
