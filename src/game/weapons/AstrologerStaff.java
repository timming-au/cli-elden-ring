package game.weapons;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.transActions.SellAction;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Purchasable;
import game.economy.transactions.Sellable;

/**
 * Astrologer's staff
 * has no special ability
 * @author Wee Jun Lin
 */
public class AstrologerStaff extends WeaponItem implements Sellable, Purchasable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public AstrologerStaff() {
        super("Astrologer's Staff", 'f', 274, "bonks", 50);
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
     * Returns purchase price of weapon
     *
     * @return purchase price of weapon
     */
    @Override
    public int getPurchasePrice() {
        return 800;
    }

    /**
     * Returns weapon selling price
     *
     * @return weapon selling price
     */
    @Override
    public int getSellingPrice() {
        return 100;
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
    public boolean purchase(Actor purchaser) {
        RuneManager rm = RuneManager.getInstance();
        if(rm.collector().hasEnoughRunes(purchaser,getPurchasePrice())){
            purchaser.addWeaponToInventory(this);
            rm.updateRune(purchaser,-getPurchasePrice());
            return true;
        }
        return false;
    }
}
