package game.economy.transactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Capability;

/**
 * Sellable interface. <br>
 * @author Kok Tim Ming
 */
public interface Sellable {

    /**
     * Removes item from Actor's inventory and updates their rune according to the item's selling price
     * @param seller Actor selling the item
     */
    void sell(Actor seller);

    /**
     * Returns item selling price
     * @return item selling price
     */
    int getSellingPrice();


    /**
     * Check if there is a trader (TRANSACTABLE) nearby.
     * @param currentLocation Current location of the performing actor
     * @param actor Actor performing the check
     */
    default boolean canSell(Location currentLocation, Actor actor) {
        boolean canSell = true;
        // for now, anybody with TRANSACTABLE is a trader
        if(actor.hasCapability(Capability.TRANSACTABLE)){
            // try to get at least 1 trader to enable trader
            for (Exit exit: currentLocation.getExits()){
                Location exitLoc = exit.getDestination();
                if(exitLoc.containsAnActor() && exitLoc.getActor().hasCapability(Capability.TRANSACTABLE)){
                    return canSell;
                }
            }
        }
        canSell = false;
        return canSell;
    }
}
