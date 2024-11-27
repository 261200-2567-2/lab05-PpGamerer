public class Necklace extends Accessory implements Mana{
    private int manaBoost;

    public Necklace(String name, int manaBoost) {
        super(name);
        this.manaBoost = manaBoost;
    }

    @Override
    public String getDescription() {
        return "Increases mana by " + manaBoost + " points.";
    }

    @Override
    public String getType() {
        return "Mana";
    }

    @Override
    public double Update() {
        if(level == 1) {
            return manaBoost;
        }
        return manaBoost + (0.5*level);
    }

    @Override
    public void Status() {
        System.out.println("Necklace : " + name + " (Level: " + level + ", Mana Boost: " + Update() + ")");
    }

    @Override
    public double getManaBoost() {
        return manaBoost;
    }
}
