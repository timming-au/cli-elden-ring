package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.FlaskOfCrimsonTears;
import game.items.RemembranceOfTheGrafted;
/**
 * Gives an item to an actor
 * @author Wee Jun Lin
 */
public class GiveItem implements DialogueEffect {
    private Item item;
    public GiveItem(Item item) {
        this.item = item;
    }

    /**
     * Returns the effect description.
     *
     * @return the effect description
     */
    @Override
    public String description(Actor actor) {
        return String.format("%s receives %s.",actor,item.toString());
    }

    /**
     * Trigger the effect.
     *
     * @param actor
     */
    @Override
    public void trigger(Actor actor) {
        actor.addItemToInventory(item);
    }
}
