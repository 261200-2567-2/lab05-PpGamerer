public class Sword extends Equipment{
    private int baseDamage;

    public Sword(String name, int level, int baseDamage) {
        super(name, level);
        this.baseDamage = baseDamage;
    }

    @Override
    public double Update() {
        return baseDamage * (1 + (0.2 * level)); // Calculate sword damage
    }

    @Override
    public void Status() {
        System.out.println("Sword: " + name + " (Level: " + level + ", Damage: " + Update() + ")");
    }
}
