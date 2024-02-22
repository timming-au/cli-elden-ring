package game.behaviors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.actions.attackActions.AttackAction;
import utils.RandomNumberGenerator;

import java.util.List;

/**
 * Attack behavior to do brainless attacks.
 * @author Kok Tim Ming
 */
public class AttackBehaviour implements Behaviour {
    private final CapabilitySet targetTypes = new CapabilitySet();
    private boolean include;

    /**
     * Constructor. Takes in a capabilitySet to exclude from attack. Enum is excluded by default.
     * @see CapabilitySet
     * @param targetTypes Accepts a list of ActorType enums to exclude from attack
     */
    public AttackBehaviour(CapabilitySet targetTypes){
        for (Enum<?> targetType: targetTypes.capabilitiesList()){
            this.targetTypes.addCapability(targetType);
        }
        this.include = false;
    }

    /**
     * Constructor. Takes in a capabilitySet to exclude from attack. Enum is excluded by default.
     * @see CapabilitySet
     * @param targetTypes Accepts a list of ActorType enums to exclude from attack
     * @param include Whether to include or exclude specified targetTypes
     */
    public AttackBehaviour(CapabilitySet targetTypes, boolean include){
        for (Enum<?> targetType: targetTypes.capabilitiesList()){
            this.targetTypes.addCapability(targetType);
        }
        this.include = include;
    }

    /**
     * Constructor. Takes in a list of ActorType enums to exclude from attack. Enum is excluded by default.
     * @param targetTypes Accepts a list of ActorType enums to exclude from attack
     */
    public AttackBehaviour(List<Enum<?>> targetTypes){
        for (Enum<?> targetType: targetTypes){
            this.targetTypes.addCapability(targetType);
        }
        this.include = false;
    }

    /**
     * Constructor. Takes in a list of ActorType enums to exclude from attack. Enum is excluded by default.
     * @param targetTypes Accepts a list of ActorType enums to exclude from attack
     * @param include Whether to include or exclude specified targetTypes
     */
    public AttackBehaviour(List<Enum<?>> targetTypes, boolean include){
        for (Enum<?> targetType: targetTypes){
            this.targetTypes.addCapability(targetType);
        }
        this.include = include;
    }

    /**
     * Constructor. Takes in an enum to exclude from attack. Enum is excluded by default.
     * @param targetType Accepts an enum to exclude from attack
     * @param include Whether to include or exclude specified targetTypes
     */
    public AttackBehaviour(Enum<?> targetType, boolean include){
        this.targetTypes.addCapability(targetType);
        this.include = include;
    }

    /**
     * Constructor. Takes in an enum to exclude from attack. Enum is excluded by default.
     * @param targetType Accepts an enum to exclude from attack
     */
    public AttackBehaviour(Enum<?> targetType){
        this.targetTypes.addCapability(targetType);
        this.include = false;
    }

    /**
     * Constructor. Will attack anybody.
     */
    public AttackBehaviour(){}

    /**
     * {@inheritDoc}
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        ActionList actions = new ActionList();

        // for every exit, find an actor and check if it has the target capability.
        // if and only if the actor has the target capability, do not give an action.
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            String direction = exit.getName();
            boolean willAttack = false;

            if (map.isAnActorAt(destination)) {
                Actor otherActor = map.getActorAt(destination);

                // wanted to use allowableActions, but enemy will attack with intrinsicWeapon?
                for (Enum<?> type: targetTypes.capabilitiesList()){
                    if(this.include){
                        if(otherActor.hasCapability(type)){
                            willAttack = true;
                            break;
                        }
                    }else{
                        if(!otherActor.hasCapability(type)){
                            willAttack = true;
                            break;
                        }
                    }

                }

                // special attack needs to be 50% chance, so we don't use intrinsic weapon
                // this is assuming enemy will always use weapon. but to argue for 'extensibility',
                // the specification also assumes enemy can only hold one weapon, but this can account for many weapons.
                if(willAttack){
                    if(actor.getWeaponInventory().size() > 0){
                        for(Weapon weapon: actor.getWeaponInventory()){
                            actions.add(new AttackAction(otherActor, direction, weapon));
                            actions.add(weapon.getSkill(otherActor, direction));
                        }
                    }else{
                        actions.add(new AttackAction(otherActor, direction));
                    }
                }
            }
        }
        if(actions.size() > 0){
            // since skills always have same number of options as regular attack, can just use RNG to 50/50
            return actions.get(RandomNumberGenerator.getRandomInt(0,actions.size()-1));
        }else{
            return null;
        }
    }
}
