public class Shield extends Equipment {
    private int baseDefense;

    public Shield(String name, int level, int baseDefense) {
        super(name, level);
        this.baseDefense = baseDefense;
    }

    @Override
    public double Update() {
        return baseDefense * (1 + (0.2 * level)); // Calculate shield defense
    }

    @Override
    public void Status() {
        System.out.println("Shield: " + name + " (Level: " + level + ", Defense: " + Update() + ")");
    }
}
