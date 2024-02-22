package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.actorActions.RestAction;
import game.actions.actorActions.SummonAction;
import game.actors.archetypes.ArchetypeFactory;
import game.actors.enemies.Invader;
import game.actors.friendlies.Ally;
import game.enums.Capability;
import utils.RandomNumberGenerator;

/**
 * Summon Sign imbued with black magic. Summons an Ally/Invader with 50/50 chance.
 * @author Kok Tim Ming
 * Modified by: Wee Jun Lin
 */
public class SummonSign extends Ground implements Summoner {
    private Location location;

    /**
     * Constructor.
     */
    public SummonSign() {
        super('=');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        actions.add(super.allowableActions(actor,location,direction));
        actions.add(new SummonAction(this));
        return actions;
    }

    @Override
    public void tick(Location location) {
        this.location = location;
        super.tick(location);
    }

    /**
     * 50% of summoning Ally, 50% of summoning Invader.
     */
    @Override
    public String summon(Actor actor) {
        new Display().println(actor + " channels a stream of black dust, calling upon a mysterious one...");
        Actor spawn;
        if(50 <= RandomNumberGenerator.getRandomInt(100)){
            spawn = new Ally(ArchetypeFactory.getRandArchetype());
        }else{
            spawn = new Invader(ArchetypeFactory.getRandArchetype());
        }
        for (Exit exit: this.location.getExits()){
            Location loc = exit.getDestination();
            if(!loc.containsAnActor() && loc.canActorEnter(spawn)){
                loc.map().addActor(spawn, loc);
                return String.format("%s was summoned at (%s,%s)",spawn, loc.x(), loc.y());
            }
        }
        return "Black dust vanishes into nothingness";
    }

    /**
     * Returns the summon effect of the Summoner
     *
     * @return the summon effect of the Summoner
     */
    @Override
    public String summonEffect() {
        return "calls upon an Ally or Invader with 50/50 chance.";
    }
}
