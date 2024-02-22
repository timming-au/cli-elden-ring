package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.ResetManager;
import game.Resettable;
import game.actions.actorActions.RemoveActorAction;
import game.enums.ActorType;
import game.enums.Capability;
import game.behaviors.AttackBehaviour;
import game.behaviors.WanderBehaviour;
import game.economy.runes.RuneDroppable;

import game.economy.runes.manager.RuneManager;
import game.enums.NPCBehavior;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * BEHOLD, DOG!<br>
 * Modified by: Kok Tim Ming
 * @author Adrian Kristanto
 */
public class LoneWolf extends Enemy implements RuneDroppable, Resettable {
    /**
     * Constructor.
     */
    public LoneWolf() {
        super("Lone Wolf", 'h', 102);
        this.addCapability(ActorType.CANINE);
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);

        this.behaviours.add(new AttackBehaviour(ActorType.CANINE));
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
     * Bite: 97 damage / 95% hit rate.
     */
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(97, "bites", 95);
    }

    /**
     * {@inheritDoc}
     * @return 55-1470 runes
     */
    @Override
    public int dropRune() {
        return RandomNumberGenerator.getRandomInt(55,1470);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
