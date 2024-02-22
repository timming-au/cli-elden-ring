package game.economy.runes.manager;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.runeActions.DropRuneAction;
import game.economy.runes.Rune;

/**
 * Registry & Singleton class
 * RuneManager could be segregated into its respective parts if it continues to grow. Segregation could be done with composition over inheritance.
 * @author Kok Tim Ming
 */
public class RuneManager {

    private static RuneManager instance;

    private final DropperManager dropper;
    private final DroppedRuneManager droppedRune;
    private final CollectorManager collector;

    private RuneManager() {
        dropper = new DropperManager();
        collector = new CollectorManager();
        droppedRune = new DroppedRuneManager();
    }

    /**
     * Returns the instantiated single instance of RuneManager.
     * @return RuneManager instance
     */
    public static RuneManager getInstance(){
        if(instance == null){
            instance = new RuneManager();
        }
        return instance;
    }

    /**
     * Unregisters an Actor from the RuneManager
     * @param actor Actor to unregister
     */
    public void unregister(Actor actor) {
        this.dropper.unregister(actor);
        this.collector.unregister(actor);
    }

    /**
     * Returns CollectorManager
     * @return CollectorManager
     */
    public CollectorManager collector() {
        return collector;
    }

    /**
     * Returns DropperManager
     * @return DropperManager
     */
    public DropperManager dropper() {
        return dropper;
    }

    /**
     * Returns DroppedRuneManager
     * @return DroppedRuneManager
     */
    public DroppedRuneManager droppedRune() {
        return droppedRune;
    }

    /**
     * Adds Rune to collector's rune
     * @param collectorActor Collector to add Rune to
     * @param rune Rune to add to collector's Rune
     */
    public void updateRune(Actor collectorActor, Rune rune){
        updateRune(collectorActor, rune.getValue());
    }

    /**
     * Adds rune value to collector's Rune
     * @param collectorActor Collector to add Rune value to
     * @param valueToAdd Rune value to add to collector's Rune
     */
    public void updateRune(Actor collectorActor, int valueToAdd){
        collector.updateRune(collectorActor, valueToAdd);
    }

    /**
     * Adds Rune dropped by giver to collector, and unregisters giver.
     * @param collectorActor Actor to give rune to
     * @param dropperActor Rune giver
     * @return Rune dropping log
     */
    public String updateRune(Actor collectorActor, Actor dropperActor){
        String res = "";
        int value = dropper.drop(dropperActor);
        if(collector.updateRune(collectorActor,value)){
            res += System.lineSeparator() + String.format("%s receives %s runes from %s",collectorActor,value,dropperActor);
        }
        return res;
    }

    /**
     * Drops Rune (Item) from collector and sets collector Rune value to 0
     * @param collectorActor Collector to drop Rune Item
     * @param location Location to drop Item at
     */
    public void dropRuneItem(Actor collectorActor, Location location){
        Rune rune = collector.getRune(collectorActor);
        collector.reset(collectorActor);

        new Display().println(new DropRuneAction(rune, location).execute(collectorActor, location.map()));

        droppedRune.register(rune,location);
    }

    /**
     * Updates collector's runes based on the rune specified.
     * Removes registered droppedRune from the HashMap.
     * @param collector Collector to update Rune
     * @param rune Rune to pick up
     */
    public void pickUpRuneItem(Actor collector, Rune rune){
        updateRune(collector, rune);
        droppedRune.unregister(rune);
    }
}
