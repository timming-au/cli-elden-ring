package game.actions.transActions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.economy.transactions.Sellable;

/**
 * Action to sell.
 * @author Kok Tim Ming
 */
public class SellAction extends Action {
    private final Sellable sellable;

    /**
     * Constructor.
     * @param sellable Sellable instance to sell
     */
    public SellAction(Sellable sellable){
        this.sellable = sellable;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        sellable.sell(actor);
        return menuDescription(actor);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s sells %s for %d runes",actor,sellable,sellable.getSellingPrice());
    }
}
