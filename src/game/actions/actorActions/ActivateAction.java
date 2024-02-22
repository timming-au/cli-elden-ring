package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Activateable;
import game.grounds.travelables.SiteOfLostGrace;
/**
 * Represents an action in the game, allowing an actor to activate the SiteOfLostGrace that enables them to fast travel to it or respawn from it.
 * @author Kok Tim Ming
 */
public class ActivateAction extends Action {
    private Activateable activateable;
    public ActivateAction(Activateable activateable){
        super();
        this.activateable = activateable;
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
        activateable.activate();
        return "";
    }

    /**
     * Returns a descriptive string
     *
     * @param actor The actor performing the action.
     * @return the text we put on the menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " activates " + activateable;
    }
}
