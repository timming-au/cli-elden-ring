package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;

/**
 * Interface for archetypes.
 * @author Kok Tim Ming
 */
public interface Archetype {
    /**
     * Returns WeaponItem instance
     * @return WeaponItem instance
     */
    WeaponItem weapon();

    /**
     * Returns Archetype hitPoints
     * @return Archetype hitPoints
     */
    int hitPoints();

    /**
     * Returns Archetype name
     * @return Archetype name
     */
    String archetypeName();
}
