package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
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
import game.weapons.Uchigatana;
import utils.RandomNumberGenerator;

public class GodrickSoldier extends Enemy implements RuneDroppable, Resettable {
    /**
     * Constructor.
     */
    public GodrickSoldier() {
        super("Godrick Soldier", 'p', 198);
        this.addWeaponToInventory(new Uchigatana());
        this.addCapability(ActorType.STORMVEIL_CASTLE);
        this.addCapability(Capability.DESPAWNABLE);
        this.addCapability(NPCBehavior.HOSTILE);
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);

        this.behaviours.add(new AttackBehaviour(ActorType.STORMVEIL_CASTLE));
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
        return RandomNumberGenerator.getRandomInt(38,70);
    }
}
