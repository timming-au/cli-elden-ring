package game.economy.runes.manager;

import edu.monash.fit2099.engine.positions.Location;
import game.economy.runes.Rune;
import game.economy.runes.RuneDroppable;
import game.enums.Status;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles dropped runes. Now many actors can drop runes as they wish.
 * When rest is called, this will protect runes by setting enum.
 * @author Kok Tim Ming
 */
public class DroppedRuneManager {
    // Runes on the ground. Assuming in the future not only Player
    // can drop Rune, allows for multiple droppedRunes to exist while
    // Player does not die.
    private final Map<Rune, Location> droppedRunes;

    /**
     * Constructor.
     */
    public DroppedRuneManager(){
        droppedRunes = new HashMap<>();
    }

    /**
     * Registers dropped Runes to the Manager, allowing RuneManager to keep track of dropped Rune location
     * @see RuneDroppable
     * @param rune Rune to register
     * @param location Location of Rune to register
     */
    public void register(Rune rune, Location location){
        this.droppedRunes.put(rune, location);
    }

    /**
     * Unregisters a dropped Rune.
     * @param rune Rune to unregister
     */
    public void unregister(Rune rune){
        this.droppedRunes.remove(rune);
    }

    /**
     * Removes rune from map and unregisters
     * @param rune Rune to remove
     */
    public void remove(Rune rune){
        droppedRunes.get(rune).removeItem(rune);
        unregister(rune);
    }

    /**
     * Protects a registered dropped rune from despawning.
     */
    public void protect(Rune rune){
        rune.addCapability(Status.DESPAWN_PROTECTION);
    }

    /**
     * Protects all registered dropped runes from despawning.
     */
    public void protectAll(){
        for(Map.Entry<Rune,Location> entry: droppedRunes.entrySet()){
            entry.getKey().addCapability(Status.DESPAWN_PROTECTION);
        }
    }

    /**
     * Removes all dropped Runes registered entirely.
     */
    public void clear(){
        droppedRunes.forEach((rune, location) -> location.removeItem(rune));
        droppedRunes.clear();
    }
}
