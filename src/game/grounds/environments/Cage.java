package game.grounds.environments;


/**
 * Cage. Contains Dog.
 * @author Wee Jun Lin
 * Modified by: Kok Tim Ming
 */
public class Cage extends EnemyEnvironment{
    public static final char REP_CHAR = '<';

    /**
     * Constructor.
     */
    public Cage() {
        super(REP_CHAR, 37,37);
    }
}
