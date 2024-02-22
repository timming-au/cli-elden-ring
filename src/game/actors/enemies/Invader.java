package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.ResetManager;
import game.Resettable;
import game.actions.actorActions.RemoveActorAction;
import game.actions.attackActions.AttackAction;
import game.actors.archetypes.Archetype;
import game.behaviors.AttackBehaviour;
import game.behaviors.Behaviour;
import game.behaviors.WanderBehaviour;
import game.economy.runes.RuneDroppable;
import game.economy.runes.manager.RuneManager;
import game.enums.ActorType;
import game.enums.NPCBehavior;
import game.enums.Status;
import utils.RandomNumberGenerator;

import java.util.ArrayList;
import java.util.List;

/**
 * Amogus.
 * @author Kok Tim Ming
 */
public class Invader extends Enemy implements RuneDroppable, Resettable {
    private Archetype archetype;

    /**
     * Constructor.
     * @param archetype Archetype to initialise Invader with
     */
    public Invader(Archetype archetype) {
        super("Invader", 'ඞ', archetype.hitPoints());
        this.addCapability(ActorType.INVADER);
        this.addCapability(NPCBehavior.HOSTILE);

        this.archetype = archetype;
        this.addWeaponToInventory(this.archetype.weapon());

        this.behaviours.add(new AttackBehaviour(ActorType.INVADER));
        this.behaviours.add(new WanderBehaviour());

        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);
        ResetManager.getInstance().registerRest(this);
    }

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
        if(this.hasCapability(Status.REST_SPECIAL)){
            this.removeCapability(Status.REST_SPECIAL);
        }else{
            this.addCapability(Status.RESET_INBOUND);
        }
    }

    /**
     * Returns the defined amount of runes to be given.
     *
     * @return Rune amount to give
     */
    @Override
    public int dropRune() {
        return RandomNumberGenerator.getRandomInt(1358,5578);
    }
}
