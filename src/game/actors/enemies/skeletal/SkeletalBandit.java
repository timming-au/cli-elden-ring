package game.actors.enemies.skeletal;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.actions.actorActions.RemoveActorAction;
import game.enums.Capability;
import game.Resettable;
import game.weapons.Scimitar;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * A flashy skeleton 💀, has 184 HP, equipped with Scimitar.
 * @see Scimitar
 * @author Kok Tim Ming
 */
public class SkeletalBandit extends SkeletalEnemy implements Resettable{
    /**
     * Constructor.
     */
    public SkeletalBandit() {
        super("Skeletal Bandit", 'b', 184);
        this.addWeaponToInventory(new Scimitar());
        this.addCapability(Capability.DESPAWNABLE);
        ResetManager.getInstance().register(this);
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
    public int dropRune(){
        return RandomNumberGenerator.getRandomInt(35, 892);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
