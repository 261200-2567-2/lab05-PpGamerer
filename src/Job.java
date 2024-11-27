public interface Job {
    void useAbility(int choice, RPGCharacter target);
    String getJobName();
    String getDescription();
    String[] getAbilities();
    default void printDescription() {
        System.out.println(getJobName() + " is " + getDescription());
    }
    default void printAbilities() {
        System.out.println(getJobName() + " Abilities:");
        for (int i = 0; i < getAbilities().length; i++) {
            System.out.println((i + 1) + ". " + getAbilities()[i]);  // Display number along with ability
        }
    }
}
