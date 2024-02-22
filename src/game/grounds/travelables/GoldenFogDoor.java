package game.grounds.travelables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.actorActions.TravelAction;
import game.enums.ActorType;

/**
 * Magical door, when used teleports an actor.
 * @author Wee Jun Lin
 * Modified by: Kok Tim Ming
 */
public class GoldenFogDoor extends Ground implements Travelable {
    private Location location;
    private String name;
    /**
     * Constructor.
     */
    public GoldenFogDoor(String name) {
        super('D');
        this.name = name;
    }

    // only Playable can enter the door
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ActorType.PLAYABLE);
    }

    public void tick(Location location) {
        this.location = location;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        for(Travelable to: TravelManager.getInstance().getAssociations(this)){
            actions.add(new TravelAction(this, to));
        }
        actions.add(super.allowableActions(actor, location, direction));
        return actions;
    }

    @Override
    public String travel(Actor actor, GameMap map) {
        map.moveActor(actor, this.location);
        return String.format("%s warps to %s at (%s,%s)", actor, this, this.location.x(), this.location.y());
    }
    @Override
    public String toString(){
        return this.name;
    }
}
