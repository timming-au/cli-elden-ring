package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.actions.actorActions.RemoveActorAction;
import game.enums.ActorType;
import game.enums.Capability;
import game.Resettable;
import game.behaviors.AttackBehaviour;
import game.behaviors.WanderBehaviour;
import game.economy.runes.RuneDroppable;
import game.economy.runes.manager.RuneManager;
import game.enums.NPCBehavior;
import game.weapons.CrabSlam;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * A huge crab.
 * Crustacean, has 407 HP, equipped with CrabSlam.
 * @see CrabSlam
 * Modified by: Wee Jun Lin<br>
 * @author Kok Tim Ming
 */
public class GiantCrab extends Enemy implements Resettable, RuneDroppable {

    /**
     * Constructor.
     */
    public GiantCrab() {
        super("Giant Crab", 'C', 407);
        this.addWeaponToInventory(new CrabSlam());
        this.addCapability(ActorType.CRUSTACEAN);
        this.addCapability(Capability.DESPAWNABLE);
        this.addCapability(NPCBehavior.HOSTILE);
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);

        this.behaviours.add(new AttackBehaviour(ActorType.CRUSTACEAN));
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
        return RandomNumberGenerator.getRandomInt(318,4961);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
