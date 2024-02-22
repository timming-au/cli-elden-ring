package game.economy.runes.manager;

import edu.monash.fit2099.engine.actors.Actor;
import game.economy.runes.Rune;

import java.util.HashMap;
import java.util.Map;

/**
 * Collector Manager class. Manages rune collectors.
 * @author Kok Tim Ming
 */
public class CollectorManager {

    private final Map<Actor, Rune> collectors;

    /**
     * Constructor.
     */
    public CollectorManager(){
        collectors = new HashMap<>();
    }

    /**
     * Registers Actors to the RuneManager, allowing them to pickup and store Runes, or drop Rune Items on the ground.
     * @param collector Actor to register
     */
    public void register(Actor collector){
        this.collectors.put(collector, new Rune(0));
    }

    /**
     * Unregisters collector from the RuneManager
     * @param collector Actor to unregister
     */
    public void unregister(Actor collector) {
        this.collectors.remove(collector);
    }

    /**
     * Resets collector's rune value to 0.
     * @param collector Collector to reset value
     */
    public void reset(Actor collector){
        Rune collectorRune = collectors.get(collector);
        if(collectorRune != null){
            collectors.put(collector, new Rune(0));
        }
    }

    /**
     * Returns collector's Rune instance
     * @param collector Collector to retrieve Rune from
     * @return Rune of specified collector, if collector doesn't exist, returns null
     */
    public Rune getRune(Actor collector){
        return collectors.get(collector);
    }

    /**
     * Adds rune value to collector's Rune
     * @param collector Collector to add Rune value to
     * @param valueToAdd Rune value to add to collector's Rune
     */
    public boolean updateRune(Actor collector, int valueToAdd){
        Rune collectorRune = collectors.get(collector);
        if(collectorRune != null){
            collectorRune.add(valueToAdd);
            return true;
        }
        return false;
    }

    /**
     * Checks if collector has enough runes.
     * @param collector Collector to check
     * @param value Value to check against collector Rune
     * @return true if collectorRune > value else false
     * @throws IllegalCallerException If collector is not registered
     */
    public boolean hasEnoughRunes(Actor collector, int value) throws IllegalCallerException{
        Rune rune = collectors.get(collector);
        if(rune != null){
            return rune.getValue() >= value;
        }else{
            throw new IllegalCallerException(String.format("%s is not a registered collector!", collector));
        }
    }
}
