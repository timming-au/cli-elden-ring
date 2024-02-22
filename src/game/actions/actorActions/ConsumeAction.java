package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;

/**
 * Action to consume. yum yum.
 * @author Kok Tim Ming
 */
public class ConsumeAction extends Action {
    private final Consumable consumable;

    /**
     * Constructs a new ConsumeAction object with the specified Consumable.
     * @param consumable the ConsumableItem to be consumed
     */
    public ConsumeAction(Consumable consumable){
        this.consumable = consumable;
    }

    /**
     * Calls the Consumable's consume method.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        return this.consumable.consume(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s consumes %s [%s]", actor, this.consumable, this.consumable.effectString());
    }
}
