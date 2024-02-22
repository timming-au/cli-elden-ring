package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
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
import game.weapons.DogSlam;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * BEHOLD, BIGGER DOG!<br>
 * @author Wee Jun Lin
 */
public class GiantDog extends Enemy implements RuneDroppable, Resettable {
    /**
     * {@inheritDoc}
     */
    public GiantDog() {
        super("Giant Dog", 'G', 693);
        this.addWeaponToInventory(new DogSlam());
        this.addCapability(ActorType.CANINE);
        this.addCapability(Capability.DESPAWNABLE);
        this.addCapability(NPCBehavior.HOSTILE);
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);

        this.behaviours.add(new AttackBehaviour(ActorType.CANINE));
        this.behaviours.add(new WanderBehaviour());
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
     * {@inheritDoc}
     */
    @Override
    public int dropRune() {
        return RandomNumberGenerator.getRandomInt(313,1808);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
