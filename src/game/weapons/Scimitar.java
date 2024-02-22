package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actions.attackActions.SpinAttackAction;
import game.actions.transActions.SellAction;
import game.economy.runes.manager.RuneManager;
import game.economy.transactions.Purchasable;
import game.economy.transactions.Sellable;

/**
 * A portable weapon primarily held by Skeletal Bandit.
 * It deals 118 damage with 88% hit rate
 * Skill: Spin Attack
 * @see SpinAttackAction
 * @author Kok Tim Ming
 */
public class Scimitar extends WeaponItem implements Sellable, Purchasable {

    private final SellAction sellAction = new SellAction(this);

    /**
     * Constructor.
     */
    public Scimitar() {
        super("Scimitar", 's', 118, "slashes", 88);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSellingPrice() {
        return 100;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getPurchasePrice() {
        return 600;
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
     * Returns SpinAttackAction
     * @return SpinAttackAction
     */
    @Override
    public Action getSkill(Actor target, String direction) {
        return new SpinAttackAction(this);
    }

}
