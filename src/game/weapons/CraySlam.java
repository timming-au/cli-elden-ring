package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attackActions.AreaAttackAction;

/**
 * An unportable weapon used by Giant Cray Fish.
 * It deals 527 damage with 100% hit rate
 * Skill: Area Attack
 * @see AreaAttackAction
 * @author Kok Tim Ming
 */
public class CraySlam extends WeaponItem {
    /**
     * Constructor.
     */
    public CraySlam() {
        super("Cray Slam", ' ', 527, "slams", 100);

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
