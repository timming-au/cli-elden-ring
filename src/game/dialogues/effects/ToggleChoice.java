package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;
import game.dialogues.Choice;

/**
 * Disables a choice.
 * @author Kok Tim Ming
 */
public class ToggleChoice implements DialogueEffect {
    private final Choice choice;
    private final boolean available;

    /**
     * Constructor.
     * @param choice Choice to disable
     */
    public ToggleChoice(Choice choice, boolean available){
        this.choice = choice;
        this.available = available;
    }

    @Override
    public String description(Actor actor) {
        return null;
    }

    @Override
    public void trigger(Actor actor) {
        choice.setAvailable(available);
    }
}
