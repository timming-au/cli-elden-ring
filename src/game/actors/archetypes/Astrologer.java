package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.AstrologerStaff;

/**
 * This was ordained by fate! Long had I forseen it.
 * The Great Astrologist "Mona" Archetype. Starts with Astrologer's Staff.
 * @author Wee Jun Lin
 */
public class Astrologer implements Archetype{
    @Override
    public WeaponItem weapon() {
        return new AstrologerStaff();
    }

    @Override
    public int hitPoints() {
        return 396;
    }

    @Override
    public String archetypeName() {
        return "Astrologer";
    }
}
