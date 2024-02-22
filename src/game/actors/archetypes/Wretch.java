package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.Club;

/**
 * Ooga Booga! Starts with Club.
 * @author Wee Jun Lin
 */
public class Wretch implements Archetype{
    @Override
    public WeaponItem weapon() {
        return new Club();
    }

    @Override
    public int hitPoints() {
        return 414;
    }

    @Override
    public String archetypeName() {
        return "Wretch";
    }
}
