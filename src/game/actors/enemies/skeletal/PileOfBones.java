package game.actors.enemies.skeletal;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.ResetManager;
import game.Resettable;
import game.enums.Status;
import game.enums.ActorType;
import game.actions.attackActions.AttackAction;
import game.actions.actorActions.RemoveActorAction;
import game.economy.runes.RuneDroppable;
import game.economy.runes.manager.RuneManager;

/**
 * Bones! 💀💀💀
 * @author Kok Tim Ming
 */
public class PileOfBones extends Actor implements RuneDroppable, Resettable{
    private int ticksToSpawn;
    int runesToDrop;
    private final SkeletalEnemy skeletal;

    /**
     * Constructor.
     * @param skeletal Revivable instance to reanimate
     * @param runesToDrop Number of runes to drop from Revivable
     */
    public PileOfBones(Actor actor, SkeletalEnemy skeletal, int runesToDrop) {
        super("Pile of Bones", 'X', 1);

        // upcasting is fine https://edstem.org/au/courses/10331/discussion/1262369?comment=2830706
        // if really not, then just pass in 1 Revivable param and 1 Actor param lol
        this.skeletal = skeletal;
        this.ticksToSpawn = 3;
        this.runesToDrop = runesToDrop;
        this.addCapability(ActorType.SKELETAL);

        // add items so can drop the original skeletal's
        for (Item i: actor.getItemInventory()){
            this.addItemToInventory(i);
        }
        for (WeaponItem w: actor.getWeaponInventory()){
            this.addWeaponToInventory(w);
        }
        RuneManager.getInstance().dropper().register(this);
        ResetManager.getInstance().register(this);
    }

    /**
     * Check if Revivable should be reanimated
     * @return true if Revivable should be reanimated else false
     */
    private boolean shouldReanimate(){
        return ticksToSpawn <= 0;
    }

    /**
     * Removes this instance, then calls ReviveAction on Revivable
     * @param map GameMap containing this actor
     */
    private Action reanimate(GameMap map){
        Location tileToRevive = map.locationOf(this);

        // remove this actor
        new RemoveActorAction().execute(this,map);

        if(!map.contains(skeletal) && !tileToRevive.containsAnActor()){
            map.addActor(skeletal,tileToRevive);
        }
        new Display().println(skeletal.revive());
        return new DoNothingAction();
    }

    /**
     * Experience the flow of time.
     * @param map Map that this is in.
     * @return Reanimate if this should reanimate else DoNothingAction
     * @see DoNothingAction
     * @see PileOfBones#shouldReanimate()
     * @see PileOfBones#reanimate(GameMap)
     */
    public Action tick(GameMap map) {
        ticksToSpawn -= 1;
        return this.shouldReanimate() ? reanimate(map) : new DoNothingAction();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESET_INBOUND)){
            return new RemoveActorAction();
        }

        return tick(map);
    }

    /**
     * {@inheritDoc}
     */
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
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
     * {@inheritDoc}
     */
    @Override
    public int dropRune() {
        return this.runesToDrop;
    }

    @Override
    public void reset() {
        this.addCapability(Status.RESET_INBOUND);
    }
}
