package game.actions.weaponSkills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.attackActions.AttackAction;
import game.weapons.Chisui;
import game.weapons.WeaponModifier;
import utils.RandomNumberGenerator;

/**
 * Bloodsuck action.
 * Inflicts and lifesteals 50-100% (random) damage from weapon with 100% accuracy.
 * @author Kok Tim Ming
 * @see Chisui
 */
public class Bloodsuck extends Action {
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
    public Bloodsuck(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = new WeaponModifier(weapon, RandomNumberGenerator.getRandomInt(weapon.damage()/2, weapon.damage()), 100);
    }

    /**
     * Perform the Action.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String res = new AttackAction(target, direction, weapon).execute(actor,map) + System.lineSeparator();
        res += String.format("%s heals for %s HP from %s's vampiric effects",actor, this.weapon.damage(), this.weapon);
        actor.heal(this.weapon.damage());
        return res;
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s bloodsucks %s with %s",actor,target,weapon);
    }
}
