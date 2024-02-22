package game.economy.transactions;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Purchasable interface.<br>
 * @author Kok Tim Ming
 */
public interface Purchasable {
    /**
     * Adds item to purchaser's inventory and deduct accordingly to purchase price if purchaser has enough runes.
     * @param purchaser Actor purchasing the item
     * @return true if purchaser has enough Runes else false
     */
    boolean purchase(Actor purchaser);

    /**
     * Returns purchase price of item
     * @return purchase price of item
     */
    int getPurchasePrice();

}
