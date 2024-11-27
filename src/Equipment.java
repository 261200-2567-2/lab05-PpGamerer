public abstract class Equipment {
    protected String name;
    protected int level;

    public Equipment(String name, int level) {
        this.name = name;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void levelUp() {
        level++;
    }

    public String getName() {
        return name;
    }

    public abstract double Update();

    public abstract void Status();
}
