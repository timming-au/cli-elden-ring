package game.grounds.environments;

import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.enemies.Enemy;
import utils.RandomNumberGenerator;

/**
 * EnemyEnvironment abstract class.
 * For now, environments can only spawn one type. In the future we can extend spawnType into a Set.
 * @author Wee Jun Lin 😳
 * Modified By: Kok Tim Ming
 */
public abstract class EnemyEnvironment extends Ground {

    private EnemyFactory spawner;
    private final int eastSpawnChance;
    private final int westSpawnChance;
    private int spawnChance;

    /**
     * Constructor.
     * @param displayChar character to display for this type of terrain
     */
    public EnemyEnvironment(char displayChar, int eastSpawnChance, int westSpawnChance) {
        super(displayChar);
        this.eastSpawnChance = eastSpawnChance;
        this.westSpawnChance = westSpawnChance;
    }

    /**
     * Spawns an actor if there are no Actors on this, and if spawnChance passes
     * @param location Location to spawn Actor
     * @param enemy Enemy to spawn
     * @param spawnChance Chance to spawn Actor
     */
    protected void spawnEnemy(Location location, Enemy enemy, int spawnChance){
        if(!location.containsAnActor() && spawnChance >= RandomNumberGenerator.getRandomInt(100)){
            location.addActor(enemy);
        }
    }

    /**
     * Sets spawner, if it is not already set.
     * @param location Location of this environment
     */
    public void setSpawner(Location location){
        if(spawner == null){
            if (isWest(location)){
                spawner = new WestMapEnemyFactory();
                spawnChance = this.westSpawnChance;
            }else{
                spawner = new EastMapEnemyFactory();
                spawnChance = this.eastSpawnChance;
            }
        }
    }

    /**
     * Spawns an enemy based on its cardinal directions (west, east).
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        setSpawner(location);
        Enemy spawn = spawner.getEnemy(this);
        if(spawn != null){
            spawnEnemy(location, spawn, this.spawnChance);
        }else{
            throw new RuntimeException("Why is enemy environment spawn null??");
        }
    }

    /**
     * Checks if this is in the west half of the map.
     * @param location Location of this
     * @return true if this is in left half of the map else false.
     */
    protected boolean isWest(Location location) {
        return location.x() < location.map().getXRange().max() / 2;
    }
}
