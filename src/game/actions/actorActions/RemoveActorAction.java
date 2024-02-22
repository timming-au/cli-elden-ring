package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.economy.runes.manager.RuneManager;

/**
 * Action to remove actor from map and unregister from Managers.
 * @author Kok Tim Ming
 */
public class RemoveActorAction extends Action {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        map.removeActor(actor);
        RuneManager.getInstance().unregister(actor);
        ResetManager.getInstance().unregister(actor);
        return menuDescription(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " has been removed from the map";
    }
}
