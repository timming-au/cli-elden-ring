package game.economy.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.*;
import game.ResetManager;
import game.Resettable;
import game.actions.runeActions.PickUpRuneAction;
import game.economy.runes.manager.RuneManager;
import game.enums.Status;

/**
 * Rune class
 * @author Kok Tim Ming
 */
public class Rune extends Item implements Resettable {
    /**
     * Constructor
     * @param value Value to instantiate Rune with
     */
    public Rune(int value){
        super("Rune", '$', true);
        setValue(value);
    }

    private int value = 0;

    /**
     * Returns Rune value
     * @return Rune value
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets Rune value
     * @param value Rune value to set
     */
    protected void setValue(int value){
        this.value = value;
    }

    /**
     * Add value to this instance
     * @param value value to add
     */
    public void add(int value){
        this.value += value;
    }

    /**
     * Add value to this instance
     * @param rune Rune value to add to this instance
     */
    public void add(Rune rune){
        this.value += rune.getValue();
    }

    /**
     * Returns the description of the instance as a String
     * @return String in format "{value} Runes"
     */
    public String toString(){
        return this.value + " Runes";
    }

    /**
     * Create and return an action to pick the Rune up.
     * If the Rune is not portable, returns null.
     *
     * @return a new PickUpRuneAction if the Rune is portable, null otherwise.
     */
    public PickUpAction getPickUpAction(Actor actor) {
        if(portable)
            return new PickUpRuneAction(this);
        return null;
    }

    /**
     * @see game.economy.runes.manager.DroppedRuneManager#remove(Rune)
     */
    @Override
    public void reset() {
        if(this.hasCapability(Status.DESPAWN_PROTECTION)){
            this.removeCapability(Status.DESPAWN_PROTECTION);
        }else{
            RuneManager.getInstance().droppedRune().remove(this);
            ResetManager.getInstance().unregister(this);
        }
    }
}
