package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attackActions.AreaAttackAction;

/**
 * An unportable weapon used by Giant Crab.<br>
 * It deals 208 damage with 90% hit rate<br>
 * Skill: Area Attack<br>
 * @see AreaAttackAction
 * @author Kok Tim Ming
 */
public class CrabSlam extends WeaponItem {
    /**
     * Constructor.
     */
    public CrabSlam() {
        super("Crab Slam", ' ', 208, "slams", 90);

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
