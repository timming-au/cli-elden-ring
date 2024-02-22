package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.actions.actorActions.ConsumeAction;

/**
 * Consumable interface class. yum yum.
 * @author Kok Tim Ming
 */
public interface Consumable{
    /**
     * Consume yum yum.
     * @param consumer The consuming Actor
     * @return Consume description
     */
    String consume(Actor consumer);

    /**
     * Returns the consumption effect.
     * @return the consumption effect
     */
    String effectString();
}
