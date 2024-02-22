package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Dummy actor with 2^31 - 1 hitpoints
 * @author Kok Tim Ming
 */
public class DummyActor extends Actor {
    /**
     * Constructor.
     */
    public DummyActor() {
        super("Dummy", '0', Integer.MAX_VALUE);
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }
}
