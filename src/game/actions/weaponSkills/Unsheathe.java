package game.actions.weaponSkills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.attackActions.AttackAction;
import game.weapons.WeaponModifier;

/**
 * Unsheathe Action.
 * @author Kok Tim Ming
 */
public class Unsheathe extends Action {
    /**
     * The Actor that is to be attacked
     */
    private final Actor target;

    /**
     * The direction of incoming attack.
     */
    private final String direction;

    /**
     * Weapon used for the attack
     */
    private final Weapon weapon;

    /**
     * Constructor.
     *
     * @param target the Actor to attack
     * @param direction the direction where the attack should be performed (only used for display purposes)
     * @param weapon weapon to perform action with
     */
    public Unsheathe(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = new WeaponModifier(weapon, weapon.damage()*2, 60);
    }

    /**
     * {@inheritDoc}
     */
    public String execute(Actor actor, GameMap map) {
        return new AttackAction(target, direction, weapon).execute(actor, map);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " unsheathes " + target + " at " + direction + " with " + weapon;
    }
}
