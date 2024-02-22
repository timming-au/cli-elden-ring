package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
/**
 * Gives an item to an actor
 * @author Kok Tim Ming
 */
public class GiveWeapon implements DialogueEffect {
    private final WeaponItem weapon;
    public GiveWeapon(WeaponItem weapon) {
        this.weapon = weapon;
    }

    /**
     * Returns the effect description.
     * @return the effect description
     */
    @Override
    public String description(Actor actor) {
        return String.format("%s receives %s",actor,weapon.toString());
    }

    @Override
    public void trigger(Actor actor) {
        actor.addWeaponToInventory(weapon);
    }
}
