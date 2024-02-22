package game.economy.runes.manager;

import edu.monash.fit2099.engine.actors.Actor;
import game.economy.runes.RuneDroppable;

import java.util.ArrayList;
import java.util.List;

/**
 * DropperManager. Handles rune dropping to Actors.
 * @author Kok Tim Ming
 */
public class DropperManager {

    // Game objects that can drop runes. Put in list because no need to store Runes,
    // can call RuneDroppable.dropRunes()
    private final List<RuneDroppable> droppers;

    /**
     * Constructor
     */
    public DropperManager(){
        droppers = new ArrayList<>();
    }

    /**
     * Registers Actors to the RuneManager, allowing them give Runes to Actors based on their defined RuneDroppable method.
     * @see RuneDroppable
     * @param dropper Actor to register
     */
    public void register(RuneDroppable dropper) {
        this.droppers.add(dropper);
    }

    /**
     * Unregisters dropper from the RuneManager
     * @param dropper Actor to unregister
     */
    public void unregister(Actor dropper) {
        this.droppers.remove(dropper);
    }

    /**
     * Drops the dropper's rune value, and unregisters from this class.
     * @param dropper Dropper to drop rune
     * @return Dropped Rune value
     */
    public int drop(Actor dropper){
        if(this.droppers.contains(dropper)){
            RuneDroppable dropperActor = this.droppers.get(this.droppers.indexOf(dropper));
            int v = dropperActor.dropRune();
            unregister(dropper);
            return v;
        }
        return 0;
    }
}
