package game.actors.friendlies;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.ResetManager;
import game.Resettable;
import game.actions.attackActions.AttackAction;
import game.actors.archetypes.Archetype;
import game.behaviors.AttackBehaviour;
import game.behaviors.Behaviour;
import game.behaviors.WanderBehaviour;
import game.enums.ActorType;
import game.enums.Status;

import java.util.ArrayList;
import java.util.List;

/**
 * Anti-amogus.
 * @author Kok Tim Ming
 */
public class Ally extends Actor implements Resettable {
    private Archetype archetype;
    protected List<Behaviour> behaviours = new ArrayList<>();

    /**
     * Constructor.
     * @param archetype Archetype to initialise Ally with
     */
    public Ally(Archetype archetype) {
        super("Ally", 'A', archetype.hitPoints());
        this.archetype = archetype;
        this.addWeaponToInventory(this.archetype.weapon());
        this.addCapability(ActorType.FRIENDLY);

        this.behaviours.add(new AttackBehaviour(ActorType.FRIENDLY));
        this.behaviours.add(new WanderBehaviour());

        ResetManager.getInstance().register(this);
        ResetManager.getInstance().registerRest(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY) && !otherActor.hasCapability(ActorType.FRIENDLY)){
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


    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        for (Behaviour behaviour : behaviours) {
            Action action = behaviour.getAction(this, map);
            if(action != null)
                return action;
        }
        return new DoNothingAction();
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
}
