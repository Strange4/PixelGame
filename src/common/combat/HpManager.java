package common.combat;

public class HpManager {
    private double currentHp;
    private double maxHp;

    public HpManager(double currentHp, double maxHp) {
        this.currentHp = currentHp;
        this.maxHp = maxHp;
    }

    public boolean isDead() {
        return (this.currentHp == 0);
    }

    /**
     * Checks if the HP is above the given percentage.
     * @param percentage
     * @return
     */
    public boolean isAbove(int percentage) {
        return ((this.currentHp * 100. / maxHp) > percentage);
    }

    // GETTERS AND SETTERS
    public double getCurrentHp() {
        return currentHp;
    }

    public void setCurrentHp(double currentHp) {
        this.currentHp = currentHp;
    }

    public double getMaxHp() {
        return maxHp;
    }

    public void setMaxHp(double maxHp) {
        this.maxHp = maxHp;
    }
}
