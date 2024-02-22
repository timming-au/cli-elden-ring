package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;


/**
 * Heals an Actor.
 * @author Wee Jun Lin
 */
public class GiveHeal implements DialogueEffect {
    private final int healValue;

    /**
     * Constructor.
     * @param healValue Heal value to give.
     */
    public GiveHeal(int healValue){
        this.healValue = healValue;
    }

    @Override
    public String description(Actor actor) {
        return String.format("%s heals for %s HP.",actor,healValue);
    }

    @Override
    public void trigger(Actor actor) {
        actor.heal(healValue);
    }
}
