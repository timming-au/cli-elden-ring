package game.grounds.environments;
import game.actors.enemies.*;
import game.actors.enemies.skeletal.HeavySkeletalSwordsman;

/**
 * BEHOLD, EAST ENEMY FACTORY
 * @author Wee Jun Lin 😎
 */
public class EastMapEnemyFactory implements EnemyFactory{
    /**
     * {@inheritDoc}
     */
    @Override
    public Enemy getEnemy(EnemyEnvironment environment) throws IndexOutOfBoundsException {
        return switch (environment.getDisplayChar()) {
            case Graveyard.REP_CHAR -> new HeavySkeletalSwordsman();
            case PuddleOfWater.REP_CHAR -> new GiantCrab();
            case GustOfWind.REP_CHAR -> new LoneWolf();
            case Cage.REP_CHAR -> new Dog();
            case Barrack.REP_CHAR -> new GodrickSoldier();
            default -> throw new IndexOutOfBoundsException("Invalid Environment: " + environment.getDisplayChar());
        };
    }
}
