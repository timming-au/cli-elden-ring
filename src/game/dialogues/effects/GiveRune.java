package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;
import game.economy.runes.manager.RuneManager;

/**
 * Give runes to an Actor.
 * @author Kok Tim Ming
 */
public class GiveRune implements DialogueEffect {
    private final int runeValue;

    /**
     * Constructor.
     * @param runeValue Rune value to give.
     */
    public GiveRune(int runeValue){
        this.runeValue = runeValue;
    }

    @Override
    public String description(Actor actor) {
        return String.format("%s receives %s runes",actor,runeValue);
    }

    @Override
    public void trigger(Actor actor) {
        RuneManager.getInstance().updateRune(actor, runeValue);
    }
}
