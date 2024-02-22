package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.enums.Status;

/**
 * A resettable interface
 * @author Kok Tim Ming
 */
public interface Revivable {
    /**
     * Performs custom behaviors when revived.
     * @return A string related to reviving
     */
    String revive();

    /**
     * Enters Actor revival state
     * @param map GameMap of actor
     * @return Action
     */
    Action enterRevival(GameMap map);

    /**
     * Checks if for Status.DEATH_INBOUND
     * @param actor Actor class to check
     * @return True if Actor has Status.DEATH_INBOUND else False
     */
    default boolean canEnterRevival(Actor actor){
        return actor.hasCapability(Status.DEATH_INBOUND);
    }
}
