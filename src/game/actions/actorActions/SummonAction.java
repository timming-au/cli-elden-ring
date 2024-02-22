package game.actions.actorActions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.grounds.Summoner;
/**
 * Represents a summon action in the game, allowing an actor to summon an ally or invader using the SummonSign.
 * @author Kok Tim Ming
 */
public class SummonAction extends Action {
    private Summoner summoner;
    public SummonAction(Summoner summoner){
        super();
        this.summoner = summoner;
    }
    @Override
    public String execute(Actor actor, GameMap map) {
        return summoner.summon(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return summoner.summonEffect();
    }
}
