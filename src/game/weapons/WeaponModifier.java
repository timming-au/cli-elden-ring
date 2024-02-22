package game.weapons;

import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A weapon substitute that will be used to represent damage and hit rate after modifiers.
 * @author Kok Tim Ming
 */
public final class WeaponModifier implements Weapon {
    private final int chanceToHit;
    private final Weapon weapon;
    private final int damage;


    /**
     * Constructor.
     * @param weapon Weapon to base modification on
     * @param damage damage after modification
     * @param chanceToHit hit accuracy after modification
     */
    public WeaponModifier(Weapon weapon, int damage, int chanceToHit){
        this.weapon = weapon;
        this.damage = damage;
        this.chanceToHit = chanceToHit;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int damage() {
        return this.damage;
    }

    /**
     * Returns passed in weapon's verb
     */
    @Override
    public String verb() {
        return this.weapon.verb();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int chanceToHit() {
        return this.chanceToHit;
    }

    /**
     * Returns passed in weapon's toString method
     */
    public String toString() {
        return this.weapon.toString();
    }
}
