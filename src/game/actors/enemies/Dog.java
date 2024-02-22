package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.ResetManager;
import game.Resettable;
import game.actions.actorActions.RemoveActorAction;
import game.behaviors.AttackBehaviour;
import game.behaviors.WanderBehaviour;
import game.economy.runes.RuneDroppable;
import game.economy.runes.manager.RuneManager;
import game.enums.ActorType;
import game.enums.Capability;
import game.enums.NPCBehavior;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * BEHOLD, CASTLE DOG!
 * @author Wee Jun Lin
 */
public class Dog extends Enemy implements RuneDroppable, Resettable {

    public Dog() {
        super("Dog",'a',104);
        this.addCapability(ActorType.STORMVEIL_CASTLE);
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);
        this.behaviours.add(new AttackBehaviour(ActorType.STORMVEIL_CASTLE));
        this.behaviours.add(new WanderBehaviour());
        this.addCapability(Capability.DESPAWNABLE);
        this.addCapability(NPCBehavior.HOSTILE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESET_INBOUND)){
            return new RemoveActorAction();
        }
        return super.playTurn(actions, lastAction, map, display);
    }
    /**
     * Bite: 101 damage / 93% hit rate.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(101, "bites", 93);
    }

    /**
     * Custom reset function for implementers.
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }

    /**
     * Returns the defined amount of runes to be given.
     *
     * @return Rune amount to give
     */
    @Override
    public int dropRune() {
        return RandomNumberGenerator.getRandomInt(52,1390);

    }
}
