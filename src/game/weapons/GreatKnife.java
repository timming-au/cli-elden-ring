package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.transActions.SellAction;
import game.actions.weaponSkills.Quickstep;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Purchasable;
import game.economy.transactions.Sellable;

/**
 * A portable weapon that can be used to attack an enemy.<br>
 * It deals 75 damage with 70% hit rate<br>
 * Skill: Quickstep<br>
 * Modified by: Wee Jun Lin
 * @see Quickstep
 * @author Kok Tim Ming
 */
public class GreatKnife extends WeaponItem implements Sellable, Purchasable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public GreatKnife() {
        super("Great Knife", '/', 75, "slashes", 70);
    }

    /**
     * Returns Quickstep
     * @see Quickstep
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new Quickstep(target, direction, this);
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
     * {@inheritDoc}
     */
    @Override
    public int getPurchasePrice() {
        return 3500;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellingPrice() {
        return 350;
    }

}
