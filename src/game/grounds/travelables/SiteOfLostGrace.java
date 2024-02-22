package game.grounds.travelables;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.actorActions.ActivateAction;
import game.actions.actorActions.TravelAction;
import game.enums.ActorType;
import game.enums.Capability;
import game.actions.actorActions.RestAction;
import game.grounds.Activateable;
import utils.FancyMessage;

/**
 * Site of Lost Grace.
 * @author Kok Tim Ming
 */
public class SiteOfLostGrace extends Ground implements Travelable, Activateable {
    private Location location;
    private String name;
    private boolean activated = false;
    /**
     * Constructor.
     */
    public SiteOfLostGrace(String name) {
        super('U');
        this.name = name;
    }

    public void activate(){
        for (String line : FancyMessage.LOST_GRACE_ACTIVATED.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(50);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        this.activated = true;
        TravelManager.getInstance().register("lostGrace",this);
    }

    @Override
    public void tick(Location location) {
        this.location = location;
    }

    // only Playable can enter the door
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ActorType.PLAYABLE);
    }

    @Override
    public String travel(Actor actor, GameMap map) {
        map.moveActor(actor, this.location);
        return String.format("%s warps to %s at (%s,%s)", actor, this, this.location.x(), this.location.y());
    }

    /**
     * {@inheritDoc}
     * Returns RestAction if Actor has Capability.GRACE_RESTABLE
     * @see Capability#GRACE_RESTABLE
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if(actor.hasCapability(Capability.GRACE_RESTABLE)){
            if(this.activated){
                for(Travelable site: TravelManager.getInstance().getAssociations(this)){
                    actions.add(new TravelAction(this,site));
                };
                actions.add(new RestAction(this));
            }else{
                actions.add(new ActivateAction(this));
            }
        }
        return actions;
    }

    public String toString(){
        return this.name;
    }
}
