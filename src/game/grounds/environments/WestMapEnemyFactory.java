package game.grounds.environments;

import game.actors.enemies.skeletal.SkeletalBandit;
import game.actors.enemies.*;

/**
 * BEHOLD, WEST ENEMY FACTORY
 * @author Wee Jun Lin 😎
 */
public class WestMapEnemyFactory implements EnemyFactory {
    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy getEnemy(EnemyEnvironment environment) throws IndexOutOfBoundsException {
        return switch (environment.getDisplayChar()) {
            case Graveyard.REP_CHAR -> new SkeletalBandit();
            case PuddleOfWater.REP_CHAR -> new GiantCrayfish();
            case GustOfWind.REP_CHAR -> new GiantDog();
            case Cage.REP_CHAR -> new Dog();
            case Barrack.REP_CHAR -> new GodrickSoldier();
            default -> throw new IndexOutOfBoundsException("Invalid Environment: " + environment.getDisplayChar());
        };
    }

}

