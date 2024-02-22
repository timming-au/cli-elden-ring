package game.actions.runeActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.PickUpAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.economy.runes.Rune;
import game.economy.runes.manager.RuneManager;

/**
 * Action to pick up Runes.
 * @author Kok Tim Ming
 */
public class PickUpRuneAction extends PickUpAction {
    private final Rune rune;

    /**
     * Constructor.
     * @param rune the rune to pick up
     */
    public PickUpRuneAction(Rune rune) {
        super(rune);
        this.rune = rune;
    }

    /**
     * Add the rune to the actor's RuneManager.
     *
     * @see Action#execute(Actor, GameMap)
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a suitable description to display in the UI
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        RuneManager.getInstance().pickUpRuneItem(actor,this.rune);
        return super.execute(actor, map);
    }

    /**
     * Describe the action in a format suitable for displaying in the menu.
     *
     * @see Action#menuDescription(Actor)
     * @param actor The actor performing the action.
     * @return a string, e.g. "Tarnished retrieves Runes (value: 5)"
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s retrieves Runes (%d)",actor,this.rune.getValue());
    }
}

