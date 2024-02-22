package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attackActions.AreaAttackAction;

/**
 * An unportable weapon used by Giant Dog.
 * It deals 314 damage with 90% hit rate
 * Skill: Area Attack
 * @see AreaAttackAction
 * @author Kok Tim Ming
 */
public class DogSlam extends WeaponItem {
    /**
     * Constructor.
     */
    public DogSlam() {
        super("Dog Slam", ' ', 314, "slams", 90);

        // make undroppable
        this.portable = false;
    }

    /**
     * Returns AreaAttackAction
     * @return AreaAttackAction
     */
    public Action getSkill(Actor target, String direction) {
        return new AreaAttackAction(target, direction, this);
    }
}
