package game.actions.runeActions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropAction;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.economy.runes.Rune;

/**
 * Action to drop a Rune Item.
 * @author Kok Tim Ming
 */
public class DropRuneAction extends DropAction {
    private final Rune rune;
    private final Location location;

    /**
     * Constructor.
     * @param item the rune to drop
     */
    public DropRuneAction(Rune item, Location location) {
        super(item);
        this.rune = item;
        this.location = location;
    }

    /**
     * When executed, add Rune to the game map.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a description of what happened that can be displayed to the user.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // add to specified location
        location.addItem(rune);

        return System.lineSeparator() + String.format("%s at (%d,%d)",menuDescription(actor),location.x(),location.y());
    }

    /**
     * {@inheritDoc}
     * @param actor The actor performing the action.
     * @return String in the format "{actor} drops {rune}"
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s drops %s",actor, this.rune);
    }
}
