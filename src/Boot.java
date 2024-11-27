public class Boot extends Accessory implements Movement {
    protected int speedBoost;

    public Boot(String name, int speedBoost) {
        super(name);
        this.speedBoost = speedBoost;
    }

    @Override
    public String getDescription() {
        return "Increases movement speed by " + speedBoost + " units.";
    }

    @Override
    public String getType() {
        return "Movement";
    }

    @Override
    public double Update() {
        if(level == 1) {
            return speedBoost;
        }
        return speedBoost + (0.5*level);
    }

    @Override
    public void Status() {
        System.out.println("Boots: " + name + " (Level: " + level + ", Speed Boost: " + Update() + ")");
    }

    @Override
    public double getSpeedBoost() {
        return speedBoost;
    }
}
