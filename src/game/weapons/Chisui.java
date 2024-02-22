package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.weaponSkills.Bloodsuck;
import game.enums.Status;

/**
 * Shuten Douji's special weapon. Translates to Bloodsucker. Cannot sell, purchase nor drop.
 * @author Kok Tim Ming
 */
public class Chisui extends WeaponItem {
    /**
     * Constructor.
     */
    public Chisui() {
        super("Chisui", '血', 550, "slashes", 100);
        this.portable = false;
        this.addCapability(Status.BLESSED);
    }

    /**
     * Returns Bloodsuck
     * @see Bloodsuck
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Bloodsuck(target, direction, this);
    }
}
