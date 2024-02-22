package game.dialogues.effects;

import edu.monash.fit2099.engine.actors.Actor;
/**
 * Hurts an Actor.
 * @author Kok Tim Ming
 */
public class InflictDamage implements DialogueEffect{
    private final int damage;
    public InflictDamage(int damage){
        this.damage = damage;
    }
    /**
     * Returns the effect description.
     *
     * @param actor Actor to act effect on
     * @return the effect description
     */
    @Override
    public String description(Actor actor) {
        return String.format("%s receives %s damage.",damage,actor);
    }

    /**
     * Trigger the effect.
     *
     * @param actor Actor to act effect on
     */
    @Override
    public void trigger(Actor actor) {
        actor.hurt(damage);
    }
}
