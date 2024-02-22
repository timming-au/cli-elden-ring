package game.items;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.ResetManager;
import game.Resettable;
import game.actions.actorActions.ConsumeAction;

/**
 * Flask of Crimson Tears.
 * @author Kok Tim Ming
 */
public class FlaskOfCrimsonTears extends Item implements Consumable, Resettable {

    private static final int MAX_CHARGES = 2;
    private final Charge charge;

    /**
     * Constructor.
     */
    public FlaskOfCrimsonTears() {
        super("Flask of Crimson Tears", ' ', false);
        ResetManager.getInstance().register(this);

        this.addAction(new ConsumeAction(this));
        charge = new Charge(MAX_CHARGES);
    }

    /**
     * {@inheritDoc}
     * Heals 250 HP.
     */
    @Override
    public String consume(Actor consumer) {
        if (this.charge.decrement()) {
            consumer.heal(250);
            return String.format("%s consumes %s to heal (+250 HP).",consumer, this);
        } else {
            return String.format("%s is out of charges",this);
        }
    }

    /**
     * {@inheritDoc}
     */
    public String effectString(){
        return "+250 HP";
    }

    @Override
    public String toString() {
        return String.format("%s %s",super.toString(),this.charge);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void reset() {
        this.charge.set(MAX_CHARGES);
    }
}
