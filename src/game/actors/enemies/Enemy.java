package game.actors.enemies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.actorActions.RemoveActorAction;
import game.enums.Capability;
import game.enums.NPCBehavior;
import game.enums.Status;
import game.actions.attackActions.AttackAction;
import game.behaviors.Behaviour;
import game.behaviors.FollowBehaviour;
import utils.RandomNumberGenerator;

import java.util.*;

/**
 * Enemy class.
 * @author Kok Tim Ming
 */
public abstract class Enemy extends Actor {

    protected List<Behaviour> behaviours = new ArrayList<>(); // inefficient but makes it simple :)

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Enemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
    }

    /**
     * Does something when an Actor with comes within 1 range.
     * By default, follow the provoker
     * @param otherActor Actor to check if will aggro
     */
    public void provoke(Actor otherActor){
        this.addCapability(NPCBehavior.PROVOKED);

        // add FollowBehavior at highest priority
        this.behaviours.add(0,new FollowBehaviour(otherActor));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        // if NPC is HOSTILE, try to provoke when a PROVOKER gets within 1 range
        if(this.hasCapability(NPCBehavior.HOSTILE) && !this.hasCapability(NPCBehavior.PROVOKED) && otherActor.hasCapability(NPCBehavior.PROVOKER)){
            provoke(otherActor);
        }

        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            // add intrinsic weapon
            actions.add(new AttackAction(this, direction));
            // for every weapon get skill, then add to possible actions
            for(WeaponItem weapon : otherActor.getWeaponInventory()){
                actions.add(new AttackAction(this, direction, weapon));
                actions.add(weapon.getSkill(this, direction));
            }
        }
        return actions;
    }

    /**
     * Returns a valid action to perform based on behaviours.
     *
     * @param map the map containing the Actor
     * @return the Action to be performed chosen from behaviours
     */
    public Action getBehavioralAction(GameMap map) {
        if(this.hasCapability(Capability.DESPAWNABLE) && !this.hasCapability(NPCBehavior.PROVOKED)){
            // 10% chance to despawn
            if (RandomNumberGenerator.getRandomInt(100) <= 10) {
                return new RemoveActorAction();
            }
        }
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }

    /**
     * {@inheritDoc}
     * Returns an action based on behaviors
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return getBehavioralAction(map);
    }
}
