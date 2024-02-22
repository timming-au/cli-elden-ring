package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.economy.runes.manager.RuneManager;

/**
 * An action executed if an actor is killed.
 * @author Adrian Kristanto
 * Modified by: Kok Tim Ming
 */
public class DeathAction extends Action {
    private final Actor attacker;

    /**
     * Constructor.
     * @param actor Death initiator. (attacker)
     */
    public DeathAction(Actor actor) {
        this.attacker = actor;
    }

    /**
     * When the target is killed, the items & weapons carried by target
     * will be dropped to the location in the game map where the target was
     *
     * @param target The actor performing the action.
     * @param map The map the actor is on.
     * @return result of the action to be displayed on the UI
     */
    @Override
    public String execute(Actor target, GameMap map) {
        String result = "";
        ActionList dropActions = new ActionList();
        // drop all items
        for (Item item : target.getItemInventory())
            dropActions.add(item.getDropAction(target));
        for (WeaponItem weapon : target.getWeaponInventory())
            dropActions.add(weapon.getDropAction(target));
        for (Action drop : dropActions)
            drop.execute(target, map);

        result += System.lineSeparator() + menuDescription(target) + RuneManager.getInstance().updateRune(attacker, target);

        // remove actor
        new RemoveActorAction().execute(target,map);

        return result;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " is killed.";
    }
}
