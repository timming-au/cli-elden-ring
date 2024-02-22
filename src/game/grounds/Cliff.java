package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.enums.ActorType;

/**
 * A class that represents a cliff.
 * Created by:
 * @author Wee Jun Lin
 * Modified by:
 *
 */
public class Cliff extends Ground {
    /**
     * Constructor.
     */
    public Cliff() {
        super('+');
    }

    // only Player can fall off
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(ActorType.PLAYABLE);
    }

    /**
     * Check whether the player steps into the cliff.
     * Kills them if they do.
     */
    @Override
    public void tick(Location location) {
        if(location.containsAnActor()){
            Actor actor = location.getActor();
            new Display().println(actor + " plummets into the abyss");
            actor.hurt(999999999); // call death action instead? No, there is no way to check
        }
    }
}
