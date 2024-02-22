package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.travelables.Travelable;
/**
 * Represents a travel action in the game, allowing an actor to travel from one location to another using GoldenFogDoor or SiteofLostGrace.
 * @author Kok Tim Ming
 */
public class TravelAction extends Action {
    private Travelable from;
    private Travelable to;

    public TravelAction(Travelable from, Travelable to) {
        super();
        this.from = from;
        this.to = to;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        return to.travel(actor, map);
    }

    @Override
    public String menuDescription(Actor actor) {
        return String.format("%s travels from %s to %s", actor, from, to);
    }
}
