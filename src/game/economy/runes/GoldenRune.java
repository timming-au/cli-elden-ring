package game.economy.runes;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.actorActions.ConsumeAction;
import game.economy.runes.manager.RuneManager;
import game.items.Consumable;
import utils.RandomNumberGenerator;

/**
 * GoldenRune class $$$$$$
 * @author Kok Tim Ming
 */
public class GoldenRune extends Item implements Consumable {
    private int minRunes = 200;
    private int maxRunes = 10000;
    private ConsumeAction consumeAction = new ConsumeAction(this);

    /**
     * Constructor with min/max rune giving
     * @param min Lower bound of number of runes to generate
     * @param max Upper bound of number of runes to generate
     */
    public GoldenRune(int min, int max) {
        super("Golden Rune", '*', true);
        this.minRunes = min;
        this.maxRunes = max;
    }

    /**
     * Constructor
     */
    public GoldenRune() {
        super("Golden Rune", '*', true);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        this.removeAction(consumeAction);
        this.addAction(consumeAction);
        super.tick(currentLocation, actor);
    }

    @Override
    public void tick(Location currentLocation) {
        this.removeAction(consumeAction);
        super.tick(currentLocation);
    }

    @Override
    public String consume(Actor consumer) {
        int earnedRunes = RandomNumberGenerator.getRandomInt(minRunes, maxRunes);
        RuneManager.getInstance().updateRune(consumer,earnedRunes);
        consumer.removeItemFromInventory(this);
        return String.format("%s earns %s runes",consumer, earnedRunes);
    }

    @Override
    public String effectString() {
        return String.format("Earns %s-%s runes",minRunes,maxRunes);
    }
}
