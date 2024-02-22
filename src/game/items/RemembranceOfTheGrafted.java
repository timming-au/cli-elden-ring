package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.CapabilitySet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.transActions.ExchangeAction;
import game.actions.transActions.SellAction;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Exchangeable;
import game.economy.transactions.Sellable;
import game.enums.Capability;
import game.weapons.AxeOfGodrick;
import game.weapons.GraftedDragon;

/**
 * Remembrance of the Grafted
 * @author Kok Tim Ming
 */
public class RemembranceOfTheGrafted extends Item implements Sellable, Exchangeable {

    private final SellAction sellAction = new SellAction(this);
    private final ExchangeAction exchangeAxe = new ExchangeAction(new AxeOfGodrick(), this);
    private final ExchangeAction exchangeDragon = new ExchangeAction(new GraftedDragon(), this);

    /**
     * Constructor.
     */
    public RemembranceOfTheGrafted() {
        super("Remembrance of the Grafted", 'O', true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // check if there is a trader nearby, if yes add SellAction else remove
        // will not show up on the first turn... it is an inevitable limitation
        this.removeAction(sellAction);
        this.removeAction(exchangeAxe);
        this.removeAction(exchangeDragon);

        if(canSell(currentLocation,actor)) {
            this.addAction(sellAction);
        }

        CapabilitySet set = new CapabilitySet();
        set.addCapability(Capability.REMEMBRANCE_ACCEPTABLE);
        if(canExchange(currentLocation, actor, set)){
            this.addAction(exchangeAxe);
            this.addAction(exchangeDragon);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sell(Actor seller) {
        seller.removeItemFromInventory(this);
        RuneManager.getInstance().updateRune(seller, getSellingPrice());
    }

    @Override
    public int getSellingPrice() {
        return 20000;
    }

    /**
     * Exchange in the item.
     *
     * @param exchanger Actor initiating the exchange
     */
    @Override
    public void exchangeIn(Actor exchanger) {
        exchanger.addItemToInventory(this);
    }

    /**
     * Exchange out the item.
     *
     * @param exchanger Actor initiating the exchange
     */
    @Override
    public void exchangeOut(Actor exchanger) {
        exchanger.removeItemFromInventory(this);
    }
}
