package game.grounds.environments;

import game.enums.ActorType;

/**
 * Gust of Wind class where doggy and wolfy spawns.
 * @author Wee Jun Lin 😳
 * Modified By: Kok Tim Ming
 * @see EnemyEnvironment
 * @see game.actors.enemies.LoneWolf
 * @see game.actors.enemies.GiantDog
 */
public class GustOfWind extends EnemyEnvironment {
    public static final char REP_CHAR = '&';
    /**
     * Constructor.
     */
    public GustOfWind() {
        super(REP_CHAR, 4, 33);
    }
}
