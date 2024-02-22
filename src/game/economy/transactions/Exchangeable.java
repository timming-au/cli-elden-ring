package game.economy.transactions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.Capability;

/**
 * Exchangeable interface.
 * @author Kok Tim Ming
 */
public interface Exchangeable {
    /**
     * Exchange in the item.
     * @param exchanger Actor initiating the exchange
     */
    void exchangeIn(Actor exchanger);

    /**
     * Exchange out the item.
     * @param exchanger Actor initiating the exchange
     */
    void exchangeOut(Actor exchanger);


    /**
     * Check if there is Capable with TRANSACTABLE and otherCapabilities nearby.
     * @param currentLocation Current location of the performing actor
     * @param actor Actor performing the check
     */
    default boolean canExchange(Location currentLocation, Actor actor, CapabilitySet otherCapabilities) {
        // check if actor has specified other capablities
        CapabilitySet newSet = new CapabilitySet();
        for(Enum <?> capability: otherCapabilities.capabilitiesList()){
            newSet.addCapability(capability);
        }
        if(actor.hasCapability(Capability.TRANSACTABLE)) {
            for (Exit exit : currentLocation.getExits()) {
                Location exitLoc = exit.getDestination();
                Actor otherActor = exitLoc.getActor();
                if (exitLoc.containsAnActor()) {
                    boolean hasOtherCapabilities = true;
                    for (Enum<?> capability : newSet.capabilitiesList()) {
                        if (!otherActor.hasCapability(capability)) {
                            hasOtherCapabilities = false;
                            break;
                        }
                    }
                    if (hasOtherCapabilities) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
