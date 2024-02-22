package game.grounds.travelables;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Travelable interface.
 * @author Kok Tim Ming
 */
public interface Travelable {
    /**
     * Travel action, can be customised by any travelable.
     *
     * @param actor Actor to perform travel on
     * @param map   Map that the actor is currently in
     */
    String travel(Actor actor, GameMap map);
}
