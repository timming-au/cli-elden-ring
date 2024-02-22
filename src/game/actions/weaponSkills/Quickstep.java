package game.actions.weaponSkills;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.attackActions.AttackAction;

/**
 * Quickstep. Attacks and hops in any 1 enter-able tile.
 * @author Kok Tim Ming<br>
 * Modified by: Wee Jun Lin
 */
public class Quickstep extends Action {
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
     * @param direction the direction where the attack should be performed (for display)
     * @param weapon weapon to attack with
     */
    public Quickstep(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     * Attacks and moves in to random tile.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        result += new AttackAction(this.target, this.direction, this.weapon).execute(actor, map);

        // return first exit not containing actor
        for(Exit exit: map.locationOf(actor).getExits()){
            Location loc = exit.getDestination();
            if(!loc.containsAnActor()){
                result += " " + new MoveActorAction(loc, exit.getName()).execute(actor, map);
                break;
            }
        }
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " quicksteps " + target + " at " + direction + " with " + weapon;
    }
}

