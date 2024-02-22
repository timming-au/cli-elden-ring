package game.actors.archetypes;

import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.weapons.GreatKnife;

/**
 * Agile bandit Archetype. Starts with GreatKnife.
 * @author Wee Jun Lin
 * @see GreatKnife
 */
public class Bandit implements Archetype{
    @Override
    public WeaponItem weapon() {
        return new GreatKnife();
    }

    @Override
    public int hitPoints() {
        return 414;
    }

    @Override
    public String archetypeName() {
        return "Bandit";
    }
}
