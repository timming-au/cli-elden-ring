package game.actions.attackActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * An Action to area attack another actor.
 * @author Kok Tim Ming
 */
public class AreaAttackAction extends Action {
    /**
     * The Actor that is to be attacked
     */
    protected Actor target;

    /**
     * The direction of incoming attack.
     */
    protected String direction;

    /**
     * Weapon used for the attack
     */
    protected Weapon weapon;

    /**
     * Constructor.
     * @param target Actor to attack
     * @param direction direction to attack target
     * @param weapon Weapon to attack with
     */
    public AreaAttackAction(Actor target, String direction, Weapon weapon) {
        this.target = target;
        this.direction = direction;
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";

        // get all exits of target, then attack whoever found
        for (Exit exit : map.locationOf(target).getExits()) {
            Location destination = exit.getDestination();

            if (map.isAnActorAt(destination)) {
                Actor a = map.getActorAt(destination);
                if(a != actor){
                    result += new AttackAction(a, exit.getName(), weapon).execute(actor, map) + System.lineSeparator();
                }
            }
        }
        result += new AttackAction(target, direction, weapon).execute(actor, map);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " area attacks " + target + " at " + direction + " with " + weapon;
    }
}
