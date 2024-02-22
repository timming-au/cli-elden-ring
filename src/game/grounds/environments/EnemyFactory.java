package game.grounds.environments;

import game.actors.enemies.Enemy;

/**
 * Interface for creating Enemy objects and spawning them based on map location
 * @author Wee Jun Lin 😎
 * @see WestMapEnemyFactory
 * @see EastMapEnemyFactory
 */
public interface EnemyFactory {

    /**
     * Spawns an enemy based on the provided ActorType.
     *
     * @param environment the environment ground
     * @return the spawned enemy instance
     * @throws IndexOutOfBoundsException if the provided spawnType is invalid
     * @author Wee Jun Lin
     */
    Enemy getEnemy(EnemyEnvironment environment) throws IndexOutOfBoundsException;

}
