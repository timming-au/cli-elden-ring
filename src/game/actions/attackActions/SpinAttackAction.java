package game.actions.attackActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * An Action to spin attack another actor.
 * @author Kok Tim Ming
 */
public class SpinAttackAction extends Action {
    private final Weapon weapon;

    /**
     * Constructor.
     * @param weapon Weapon to perform spin attack
     */
    public SpinAttackAction(Weapon weapon){
        this.weapon = weapon;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = "";
        // get all exits of target, then attack whoever found
        for (Exit exit : map.locationOf(actor).getExits()) {
            Location destination = exit.getDestination();

            if (map.isAnActorAt(destination)) {
                Actor a = map.getActorAt(destination);
                result += new AttackAction(a, exit.getName(), weapon).execute(actor, map) + System.lineSeparator();
            }
        }
        result += new AttackAction(actor, "self", weapon).execute(actor, map);
        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " spin attacks with " + weapon;
    }
}

