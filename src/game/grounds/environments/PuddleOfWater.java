package game.grounds.environments;

import game.enums.ActorType;

/**
 * Puddle of Water class where crabs spawns. 🦀🦀🦀
 * @author Wee Jun Lin 😳
 * Modified By: Kok Tim Ming
 * @see EnemyEnvironment
 * @see game.actors.enemies.GiantCrab
 * @see game.actors.enemies.GiantCrayfish
 */
public class PuddleOfWater extends EnemyEnvironment {
    public static final char REP_CHAR = '~';
    /**
     * Constructor.
     */
    public PuddleOfWater() {
        super(REP_CHAR, 1, 2);
    }

}
