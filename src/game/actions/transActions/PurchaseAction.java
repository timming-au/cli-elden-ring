package game.actions.transActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.economy.runes.Rune;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Purchasable;

/**
 * Action to make purchases.
 * @author Kok Tim Ming
 */
public class PurchaseAction extends Action {
    private final Actor trader;
    private final Purchasable purchasable;

    /**
     * Constructor.
     * @param trader Actor to purchase from
     * @param purchasable Purchasable instance to purchase
     */
    public PurchaseAction(Actor trader, Purchasable purchasable){
        this.trader = trader;
        this.purchasable = purchasable;
    }

    /**
     * {@inheritDoc}
     * Deducts runes according to price set by the purchasable.
     * @see Purchasable
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if(purchasable.purchase(actor)){
            return menuDescription(actor);
        }
        Rune rune = RuneManager.getInstance().collector().getRune(actor);
        return String.format("%s does not have enough Runes (%s/%s) ",actor,rune.getValue(),purchasable.getPurchasePrice()) ;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s purchases %s from %s for %d runes",actor,purchasable,this.trader,purchasable.getPurchasePrice());
    }
}
