package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.transActions.SellAction;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Exchangeable;
import game.economy.transactions.Sellable;

/**
 * Grafted Dragon.
 * has no special ability
 * @author Kok Tim Ming
 */
public class GraftedDragon extends WeaponItem implements Sellable, Exchangeable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public GraftedDragon() {
        super("Grafted Dragon", 'N', 89, "burns", 90);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void sell(Actor seller) {
        seller.removeWeaponFromInventory(this);
        RuneManager.getInstance().updateRune(seller, getSellingPrice());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // check if there is a trader nearby, if yes add SellAction else remove
        // will not show up on the first turn... it is a limitation
        this.removeAction(sellAction);
        if(canSell(currentLocation,actor)) {
            this.addAction(sellAction);
        }
    }

    /**
     * Returns item selling price
     *
     * @return item selling price
     */
    @Override
    public int getSellingPrice() {
        return 200;
    }

    /**
     * Exchange in the item.
     *
     * @param exchanger Actor initiating the exchange
     */
    @Override
    public void exchangeIn(Actor exchanger) {
        exchanger.addWeaponToInventory(this);
    }

    /**
     * Exchange out the item.
     *
     * @param exchanger Actor initiating the exchange
     */
    @Override
    public void exchangeOut(Actor exchanger) {
        exchanger.removeWeaponFromInventory(this);
    }
}
