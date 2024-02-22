package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Uchigatana;

/**
 * Samurai from Meiji era. Starts with Uchigatana.
 * @see Uchigatana
 */
public class Samurai implements Archetype {

    @Override
    public WeaponItem weapon() {
        return new Uchigatana();
    }

    @Override
    public int hitPoints() {
        return 455;
    }

    @Override
    public String archetypeName() {
        return "Samurai";
    }
}
