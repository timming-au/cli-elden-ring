package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * For implementing dialogue effects. While similar to Action, its methods are not optimal.
 * @author Kok Tim Ming
 */
public interface DialogueEffect {

    /**
     * Returns the effect description.
     * @param actor Actor to act effect on
     * @return the effect description
     */
    String description(Actor actor);

    /**
     * Trigger the effect.
     * @param actor Actor to act effect on
     */
    void trigger(Actor actor);
}
