package game.actors.enemies.skeletal;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.actions.actorActions.RemoveActorAction;
import game.enums.Capability;
import game.Resettable;
import game.actors.Revivable;
import game.economy.runes.RuneDroppable;
import game.weapons.Grossmesser;
import game.enums.Status;
import utils.RandomNumberGenerator;

/**
 * A thick skeleton, has 153 HP, equipped with Grossmesser.
 * @see Grossmesser
 * @author Kok Tim Ming
 */
public class HeavySkeletalSwordsman extends SkeletalEnemy implements Resettable, RuneDroppable{

    /**
     * Constructor.
     */
    public HeavySkeletalSwordsman() {
        super("Heavy Skeletal Swordsman", 'q', 153);
        this.addWeaponToInventory(new Grossmesser());
        this.addCapability(Capability.DESPAWNABLE);
        ResetManager.getInstance().register(this);
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
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
