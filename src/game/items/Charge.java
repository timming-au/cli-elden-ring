package game.items;

/**
 * Handles charge (number of uses) of anything.
 * @author Kok Tim Ming
 */
public class Charge {
    private int useCharge;

    private final int maxCharge;

    /**
     * Constructor. Sets starting charge to max charge
     * @param maxCharge Maximum charge
     */
    public Charge(int maxCharge){
        this.maxCharge = maxCharge;
        this.useCharge = maxCharge;
    }

    /**
     * Constructor. Allows starting charge to be more than max charges for whatever circumstance.
     * @param startCharge Starting charge
     * @param maxCharge Maximum charge
     */
    public Charge(int startCharge, int maxCharge){
        this.maxCharge = maxCharge;
        this.useCharge = startCharge;
    }

    /**
     * Decrement charge if more than 0.
     * @return true if charge is more than 0 else false
     */
    protected boolean decrement(){
        if (this.enough()){
            this.useCharge--;
            return true;
        }
        return false;
    }

    /**
     * Checks if charge more than 0.
     * @return true if charge more than 0 else false
     */
    public boolean enough(){
        return this.useCharge > 0;
    }

    /**
     * Decrement charge if less than max charge.
     * @return true if less than max charge else false
     */
    protected boolean increment(){
        if (this.useCharge < maxCharge){
            this.useCharge++;
            return true;
        }
        return false;
    }

    /**
     * Sets charge
     * @param charge Charge to set
     */
    protected void set(int charge){
        this.useCharge = charge;
    }

    /**
     * Returns charge
     * @return current charge value
     */
    public int get(){
        return this.useCharge;
    }

    /**
     * Returns max charge
     * @return max charge value
     */
    public int getMax(){
        return this.maxCharge;
    }

    @Override
    public String toString() {
        return String.format("%s/%s",useCharge,maxCharge);
    }
}
