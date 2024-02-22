package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;

/**
 * ResetAction class. Used to execute resets.
 */
public class ResetAction extends Action {
    /**
     * {@inheritDoc}
     * Runs Reset Manager to reset the game.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ResetManager.getInstance().run();
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
