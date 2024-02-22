package game.actors.enemies.skeletal;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.actorActions.RemoveActorAction;
import game.actors.enemies.Enemy;
import game.enums.ActorType;
import game.enums.Status;
import game.behaviors.AttackBehaviour;
import game.behaviors.WanderBehaviour;
import game.economy.runes.RuneDroppable;
import game.economy.runes.manager.RuneManager;

/**
 * Abstract Skeletal class. 💀💀💀
 * @author Kok Tim Ming
 */
public abstract class SkeletalEnemy extends Enemy implements RuneDroppable {
    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public SkeletalEnemy(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);

        this.addCapability(ActorType.SKELETAL);
        RuneManager.getInstance().dropper().register(this);

        this.behaviours.add(new AttackBehaviour(ActorType.SKELETAL));
        this.behaviours.add(new WanderBehaviour());
    }

    /**
     * {@inheritDoc}
     * or tries to revive if killed.
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        if(this.hasCapability(Status.RESET_INBOUND)){
            return new RemoveActorAction();
        }

        if(this.hasCapability(Status.DEATH_INBOUND)){
            return enterRevival(map);
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * Hurt method. Revivables will cheat death, as shown in example.
     * @param points number of hitpoints to deduct.
     */
    @Override
    public void hurt(int points) {
        super.hurt(points);
        if(hitPoints <= 0){
            hitPoints = 1;
            this.addCapability(Status.DEATH_INBOUND);
        }
    }

    /**
     * Recovers max HP and registers to RuneManager
     * @return A string related to reviving
     */
    public String revive() {
        RuneManager.getInstance().dropper().register(this);
        this.heal(this.getMaxHp());
        return this + " revives";
    }

    /**
     * Removes this Actor, then turns into Pile Of Bones.
     * @param map Map that actor is in
     * @return An Action that will be performed when entering revival
     */
    public Action enterRevival(GameMap map) {
        this.removeCapability(Status.DEATH_INBOUND);
        Location loc = map.locationOf(this);
        new RemoveActorAction().execute(this,map);
        Actor to = new PileOfBones(this,this, this.dropRune());
        map.addActor(new PileOfBones(this,this, this.dropRune()), loc);
        new Display().println(this + " turns into " + to + ".");
        return new DoNothingAction();
    }


}
