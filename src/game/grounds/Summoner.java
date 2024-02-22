package game.grounds;


// For requirement 3, summoner interface was created to allow future game objects
// that can summon other game objects be implemented easily. The summoner interface
// will allow SummonAction to display different kinds of summons

import edu.monash.fit2099.engine.actors.Actor;

/**
 * Summoner interface. For any game object that can summon another game object.
 */
public interface Summoner {
    /**
     * Summon.
     * @param actor Actor performing the summon
     * @return A summon string
     */
    String summon(Actor actor);

    /**
     * Returns the summon effect of the Summoner
     * @return the summon effect of the Summoner
     */
    String summonEffect();
}
