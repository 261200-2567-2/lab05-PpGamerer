public abstract class Accessory {
    protected String name;
    protected int level;

    public Accessory(String name) {
        this.name = name;
        this.level = 1;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public abstract String getType();

    public void levelUp() {
        level++;
    }

    public abstract String getDescription();

    public abstract double Update();

    public void Status() {
        System.out.println(name + " (Level: " + level + ")");
        System.out.println("Effect: " + getDescription() + " (Value: " + Update() + ")");
    }
}
