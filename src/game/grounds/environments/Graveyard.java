package game.grounds.environments;

import game.enums.ActorType;
import game.actors.enemies.skeletal.HeavySkeletalSwordsman;
import game.actors.enemies.skeletal.SkeletalBandit;

/**
 * Graveyard class where skeleton spawns. 💀💀💀
 * @author Wee Jun Lin 😳
 * Modified By: Kok Tim Ming
 * @see EnemyEnvironment
 * @see SkeletalBandit
 * @see HeavySkeletalSwordsman
 */
public class Graveyard extends EnemyEnvironment {
    public static final char REP_CHAR = 'n';
    /**
     * Constructor.
     */
    public Graveyard() {
        super(REP_CHAR, 27, 27);
    }
}
