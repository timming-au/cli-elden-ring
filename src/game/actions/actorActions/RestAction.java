package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

import game.economy.runes.manager.RuneManager;
import game.grounds.travelables.SiteOfLostGrace;

/**
 * Represents a rest action in the game, allowing an actor to rest at a specific location.
 * @author Kok Tim Ming
 */
public class RestAction extends Action {
    private final SiteOfLostGrace siteOfLostGrace;

    /**
     * Constructor
     * @param siteOfLostGrace Site Of Lost Grace instance to perform this action on
     */
    public RestAction(SiteOfLostGrace siteOfLostGrace){
        this.siteOfLostGrace = siteOfLostGrace;
    }
    /**
     * Executes the RestAction and resets the actor's state in the game map.
     *
     * @param actor The actor performing the rest action
     * @param map The game map where the action is taking place
     * @return A string description of the rest action performed
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().droppedRune().protectAll();
        new ResetAction().execute(actor, map);
        return menuDescription(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s rests on %s",actor,siteOfLostGrace);
    }
}
