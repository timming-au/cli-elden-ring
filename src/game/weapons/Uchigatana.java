package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.transActions.SellAction;
import game.actions.weaponSkills.Unsheathe;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Purchasable;
import game.economy.transactions.Sellable;

/**
 * A portable weapon that can be used to attack an enemy.<br>
 * It deals 115 damage with 80% hit rate.<br>
 * Skill: Unsheathe<br>
 * @see Unsheathe
 * @author Kok Tim Ming
 */
public class Uchigatana extends WeaponItem implements Sellable, Purchasable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public Uchigatana() {
        super("Uchigatana", ')', 115, "slashes", 80);
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
     * Returns Unsheathe
     * @see Unsheathe
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Unsheathe(target, direction, this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPurchasePrice() {
        return 5000;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellingPrice() {
        return 500;
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
