package game.grounds.environments;


/**
 * Barrack. Spawns Godrick Soldiers.
 * @author Kok Tim Ming
 */
public class Barrack extends EnemyEnvironment{
    public static final char REP_CHAR = 'B';

    /**
     * Constructor.
     */
    public Barrack() {
        super(REP_CHAR,45,45);
    }


}
