package game.dialogues;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * Dialogue Action. Executes a dialogue.
 * @author Kok Tim Ming
 */
public class DialogueAction extends Action {
    private final Dialogue dialogue;
    private final Actor other;

    /**
     * Constructor.
     * @param dialogue Dialogue to execute
     * @param other Actor providing the Dialogue
     */
    public DialogueAction(Dialogue dialogue, Actor other){
        this.dialogue = dialogue;
        this.other = other;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(Actor initiator, GameMap map) {
        new DialogueSystem(initiator, other, this.dialogue).start();

        return menuDescription(initiator);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s talks to %s",actor,other);
    }
}
